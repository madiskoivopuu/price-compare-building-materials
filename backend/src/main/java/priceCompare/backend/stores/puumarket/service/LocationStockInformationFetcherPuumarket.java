package priceCompare.backend.stores.puumarket.service;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.dto.StockDto;
import priceCompare.backend.dto.StockInLocationsDto;

import java.util.HashMap;
import java.util.Map;

public class LocationStockInformationFetcherPuumarket {
    public LocationStockInformationFetcherPuumarket() {
    }

    private StockDto parseStock(Element htmlCityStockEl) {
        Elements locAndStock = htmlCityStockEl.getElementsByTag("span");
        String locationDisplayName = locAndStock.getFirst().text();
        String quantity = locAndStock.get(1).text();

        LocationDto storeLoc = PuumarketStoreLocation.locationFromSourceName(locationDisplayName);
        if(storeLoc == null) {
            System.err.printf("Puumarket stock fetcher: error mapping store name %s to a Puumarket store location\n", locationDisplayName);
            return null;
        }

        if(quantity.equals("-"))
            return StockDto.builder()
                    .location(storeLoc)
                    .quantityText("0 tk")
                    .build();

        return StockDto.builder()
                .location(storeLoc)
                .quantityText(quantity)
                .additionalNotes(
                        quantity.equals("Tellitav") ? "\"Tellitav\" märgistusega tooted tellib Puumarket otse tarnijalt." : ""
                )
                .build();
    }

    public StockInLocationsDto parseStockInfo(Element htmlProductEl) {
        Map<LocationDto, StockDto> stockForLoc = new HashMap<>();

        // add all cities by default, because product element might not include all available locations
        for(PuumarketStoreLocation storeLoc : PuumarketStoreLocation.values()) {
            stockForLoc.put(storeLoc.getLocation(), StockDto.builder()
                            .location(storeLoc.getLocation())
                            .quantityText("0 tk")
                            .build()
            );
        }

        Elements stockInLocationsEls = htmlProductEl.select("li.d-flex:not(.stocks-footer)");
        for(Element stockInLocationEl : stockInLocationsEls) {
            StockDto parsedStock = parseStock(stockInLocationEl);
            if(parsedStock != null)
                stockForLoc.put(parsedStock.getLocation(), parsedStock);
        }

        return StockInLocationsDto.builder()
                .locations(stockForLoc.values().stream().toList())
                .build();
    }
}

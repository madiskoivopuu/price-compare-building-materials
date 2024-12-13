package priceCompare.backend.stores.puumarket.service;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.dto.StockDto;
import priceCompare.backend.dto.StockInLocationsDto;
import java.util.HashMap;
import java.util.Map;

@Service
public class PuumarketStockFetcher {

    private StockDto parseStock(Element htmlCityStockEl) {
        Elements locAndStock = htmlCityStockEl.getElementsByTag("span");
        String locationDisplayName = locAndStock.getFirst().text();
        String quantity = locAndStock.get(1).text();

        LocationDto storeLoc = PuumarketStoreLocation.locationFromSourceName(locationDisplayName);
        if(storeLoc == null) {
            System.err.printf("Puumarket stock fetcher: failed mapping store name %s to a Puumarket store location\n", locationDisplayName);
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
        Elements stockInLocationsEls = htmlProductEl.select("li.d-flex.m-0");

        // add all cities by default, because product element might not include all available locations
        for(PuumarketStoreLocation storeLoc : PuumarketStoreLocation.values()) {
            stockForLoc.put(storeLoc.getLocation(), StockDto.builder()
                            .location(storeLoc.getLocation())
                            .quantityText(
                                    storeLoc.equals(PuumarketStoreLocation.TALLINN_1_VALISLADU) && !htmlProductEl.text().toLowerCase().contains("saadaval ainult laojäägiga esinduses")
                                            ? "Tellitav"
                                            : "0 tk"
                            )
                            .build()
            );
        }

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

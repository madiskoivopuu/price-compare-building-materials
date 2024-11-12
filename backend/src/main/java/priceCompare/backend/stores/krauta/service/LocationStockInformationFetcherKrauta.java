package priceCompare.backend.stores.krauta.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.dto.StockDto;
import priceCompare.backend.dto.StockInLocationsDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LocationStockInformationFetcherKrauta {

    public StockInLocationsDto fetchLocationStockInfo(String url) throws IOException {
        List<StockDto> stockDtoList = new ArrayList<>();

        Document document = Jsoup.connect(url).get();
        Elements cityBlocks = document.select("div.city-block.clearfix");

        for (Element cityBlock : cityBlocks) {
            stockDtoList.addAll(parseCityBlock(cityBlock));
        }

        stockDtoList = addLocationsWithNoStock(stockDtoList);
        return StockInLocationsDto.builder().locations(stockDtoList).build();
    }

    public List<StockDto> parseCityBlock(Element cityBlock) {
        List<StockDto> stockDtos = new ArrayList<>();
        Elements storeRows = cityBlock.select("table.stock-stores-widths tr");

        for (Element storeRow : storeRows) {
            StockDto stockDto = parseStoreRow(storeRow);
            if (stockDto != null) {
                stockDtos.add(stockDto);
            }
        }
        return stockDtos;
    }

    public StockDto parseStoreRow(Element storeRow) {
        String storeDetails = storeRow.selectFirst("td.stock-first-td a").text();
        LocationDto locationDto = KrautaStoreLocation.locationFromSourceName(storeDetails);
        if (locationDto == null) {
            return null;
        }

        Integer quantity = parseStockQuantityForRow(storeRow);
        boolean infoUnavailable = (quantity == null);
        String additionalNotes = infoUnavailable ? "Stock information unavailable" : "";

        return StockDto.builder()
                .location(locationDto)
                .quantityText(String.format("%s tk", quantity))
                .additionalNotes(additionalNotes)
                .build();
    }

    private Integer parseStockQuantityForRow(Element storeRow) {
        String stockQuantityText = storeRow.select("td").get(3).text();
        return parseQuantityFromText(stockQuantityText);
    }

    public Integer parseQuantityFromText(String stockQuantityText) {
        try {
            return Integer.parseInt(stockQuantityText.replace("tk", "").trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public List<StockDto> addLocationsWithNoStock(List<StockDto> stockDtoList) {
        List<StockDto> stockDtoListWithLocationsWithNoStock = new ArrayList<>(stockDtoList);

        Set<LocationDto> existingLocations = stockDtoList.stream()
                .map(StockDto::getLocation)
                .collect(Collectors.toSet());

        for (KrautaStoreLocation krautaStoreLocation : KrautaStoreLocation.values()) {
            LocationDto location = krautaStoreLocation.getLocation();
            if (!existingLocations.contains(location)) {
                stockDtoListWithLocationsWithNoStock.add(createEmptyStockDto(location));
            }
        }
        return stockDtoListWithLocationsWithNoStock;
    }

    private static StockDto createEmptyStockDto(LocationDto location) {
        return StockDto.builder()
                .location(location)
                .quantityText("0 tk")
                .build();
    }
}

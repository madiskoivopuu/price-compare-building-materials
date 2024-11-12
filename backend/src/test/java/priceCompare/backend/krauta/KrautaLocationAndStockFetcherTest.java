package priceCompare.backend.krauta;

import static org.junit.jupiter.api.Assertions.*;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.dto.StockDto;
import priceCompare.backend.enums.LocationName;
import priceCompare.backend.stores.krauta.service.KrautaStoreLocation;
import priceCompare.backend.stores.krauta.service.LocationStockInformationFetcherKrauta;
import java.util.List;


class KrautaLocationAndStockFetcherTest {

    private LocationStockInformationFetcherKrauta stockLocationFetcher;

    @BeforeEach
    void setUp() {
        stockLocationFetcher = new LocationStockInformationFetcherKrauta();
    }

    @Test
    void parseCityBlock_ShouldParseAndReturnStockDtoList() {
        Element cityBlock = new Element("div").addClass("city-block").addClass("clearfix");
        cityBlock.append("<div class='near-shops-list'>" +
                "<div class='table-wrapper'>" +
                "<table class='stock-stores-widths'>" +
                "<tr>" +
                "<td class='stock-first-td'><a href='/stores/1'>K-rauta Haabersti, Paldiski mnt 108a</a></td>" +
                "<td class='stock-second-td'></td>" +
                "<td>30.10.2024</td>" +
                "<td>83 tk</td>" +
                "</tr>" +
                "<tr>" +
                "<td class='stock-first-td'><a href='/stores/2'>K-rauta Tondi, Tammsaare tee 49</a></td>" +
                "<td class='stock-second-td'></td>" +
                "<td>30.10.2024</td>" +
                "<td>92 tk</td>" +
                "</tr>" +
                "</table>" +
                "</div>" +
                "</div>");

        List<StockDto> result = stockLocationFetcher.parseCityBlock(cityBlock);

        assertEquals(2, result.size());
        assertEquals(LocationName.TALLINN, result.get(0).getLocation().getLocationName());
        assertEquals("83 tk", result.get(0).getQuantityText());
        assertEquals(LocationName.TALLINN, result.get(1).getLocation().getLocationName());
        assertEquals("92 tk", result.get(1).getQuantityText());
    }

    @Test
    void parseStoreRow_ShouldReturnCorrectStockDto() {
        Element storeRow = new Element("tr");
        storeRow.append("<td class='stock-first-td'><a href='/stores/4'>K-rauta Pärnu, Papiniidu 2a</a></td>");
        storeRow.append("<td class='stock-second-td'></td>");
        storeRow.append("<td>22.10.2024</td>");
        storeRow.append("<td>187 tk</td>");

        StockDto result = stockLocationFetcher.parseStoreRow(storeRow);

        assertNotNull(result);
        assertEquals(LocationName.PARNU, result.getLocation().getLocationName());
        assertEquals("187 tk", result.getQuantityText());
    }

    @Test
    void parseQuantityFromText_ShouldParseCorrectQuantity() {
        assertEquals(83, stockLocationFetcher.parseQuantityFromText("83 tk"));
        assertEquals(0, stockLocationFetcher.parseQuantityFromText("0 tk"));
        assertNull(stockLocationFetcher.parseQuantityFromText("N/A"));
        assertNull(stockLocationFetcher.parseQuantityFromText("unknown"));
    }

    @Test
    void addLocationsWithNoStock_ShouldAddMissingLocationsWithZeroStock() {
        LocationDto existingLocation = KrautaStoreLocation.HAABERSTI.getLocation();

        StockDto stockDto = StockDto.builder()
                .location(existingLocation)
                .quantityText("10 tk")
                .build();

        List<StockDto> stockDtoList = List.of(stockDto);
        List<StockDto> result = stockLocationFetcher.addLocationsWithNoStock(stockDtoList);

        for (KrautaStoreLocation location : KrautaStoreLocation.values()) {
            assertTrue(result.stream().anyMatch(dto -> dto.getLocation().equals(location.getLocation())));
        }

        for (StockDto dto : result) {
            if (!dto.getLocation().equals(existingLocation)) {
                assertEquals("0 tk", dto.getQuantityText());
            }
        }
    }
}

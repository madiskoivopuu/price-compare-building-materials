package priceCompare.backend.puumarket;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.dto.StockDto;
import priceCompare.backend.dto.StockInLocationsDto;
import priceCompare.backend.stores.puumarket.service.LocationStockInformationFetcherPuumarket;
import priceCompare.backend.stores.puumarket.service.PuumarketStoreLocation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PuumarketLocationStockInfoFetcherTest {
    @Test
    void testAllLocationsParsedCorrectly() throws IOException {
        // prep
        final String productHtml = Files.readString(Path.of("src/test/resources/puumarket/search_res_single_product_w_all_locs.txt"));
        final Document productEl = Jsoup.parseBodyFragment(productHtml);
        final LocationStockInformationFetcherPuumarket stockFetcher = new LocationStockInformationFetcherPuumarket();
        Map<LocationDto, String> expectedStock = Map.ofEntries(
                Map.entry(PuumarketStoreLocation.TALLINN_1.getLocation(), "1143 jm"),
                Map.entry(PuumarketStoreLocation.TALLINN_1_VALISLADU.getLocation(), "Tellitav"),
                Map.entry(PuumarketStoreLocation.TALLINN_2.getLocation(), "183 jm"),
                Map.entry(PuumarketStoreLocation.RAKVERE.getLocation(), "360 jm"),
                Map.entry(PuumarketStoreLocation.PARNU.getLocation(), "1740 jm"),
                Map.entry(PuumarketStoreLocation.TARTU.getLocation(), "228 jm")
        );


        StockInLocationsDto stock = stockFetcher.parseStockInfo(productEl);
        assertEquals(PuumarketStoreLocation.values().length, stock.getLocations().size(), "Stock info fetcher should return stock in all available stores for Puumarket");

        for(StockDto stockInLoc : stock.getLocations()) {
            assertTrue(expectedStock.containsKey(stockInLoc.getLocation()), "Stock location should contain " + stockInLoc.getLocation());

            String expectedQuantity = expectedStock.get(stockInLoc.getLocation());
            assertEquals(expectedQuantity, stockInLoc.getQuantityText(), String.format("Expected %s to have quantity %s, but in reality it had %s", stockInLoc.getLocation(), expectedQuantity, stockInLoc.getQuantityText()));
        }
    }

    @Test
    void testHtmlOnlyHasSingleLocStock() throws IOException {
        // prep
        final String productHtml = Files.readString(Path.of("src/test/resources/puumarket/search_res_single_product_w_one_loc.txt"));
        final Document productEl = Jsoup.parseBodyFragment(productHtml);
        final LocationStockInformationFetcherPuumarket stockFetcher = new LocationStockInformationFetcherPuumarket();
        Map<LocationDto, String> expectedStock = Map.ofEntries(
                Map.entry(PuumarketStoreLocation.TALLINN_1.getLocation(), "0 tk"),
                Map.entry(PuumarketStoreLocation.TALLINN_1_VALISLADU.getLocation(), "0 tk"),
                Map.entry(PuumarketStoreLocation.TALLINN_2.getLocation(), "2683.8 jm"),
                Map.entry(PuumarketStoreLocation.RAKVERE.getLocation(), "0 tk"),
                Map.entry(PuumarketStoreLocation.PARNU.getLocation(), "0 tk"),
                Map.entry(PuumarketStoreLocation.TARTU.getLocation(), "0 tk")
        );


        StockInLocationsDto stock = stockFetcher.parseStockInfo(productEl);
        assertEquals(PuumarketStoreLocation.values().length, stock.getLocations().size(), "Stock info fetcher should return stock in all available stores for Puumarket");

        for(StockDto stockInLoc : stock.getLocations()) {
            assertTrue(expectedStock.containsKey(stockInLoc.getLocation()), "Stock location should contain " + stockInLoc.getLocation());

            String expectedQuantity = expectedStock.get(stockInLoc.getLocation());
            assertEquals(expectedQuantity, stockInLoc.getQuantityText(), String.format("Expected %s to have quantity %s, but in reality it had %s", stockInLoc.getLocation(), expectedQuantity, stockInLoc.getQuantityText()));
        }
    }
}

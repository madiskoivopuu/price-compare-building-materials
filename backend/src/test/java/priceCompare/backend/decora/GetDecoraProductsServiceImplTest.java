package priceCompare.backend.decora;

import org.checkerframework.checker.units.qual.C;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import priceCompare.backend.HttpClient.HttpClientService;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.dto.StockDto;
import priceCompare.backend.dto.StockInLocationsDto;
import priceCompare.backend.stores.decora.service.DecoraAPIs;
import priceCompare.backend.stores.decora.service.DecoraStoreLocation;
import priceCompare.backend.stores.decora.service.GetDecoraProductsServiceImpl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetDecoraProductsServiceImplTest {
    @Test
    public void testDecoraSearchReturnsProductsWithStock() throws IOException {
        // mock prep
        final String query = "kipsplaat";
        final int EXPECTED_NUM_RESULTS = 2;

        DecoraAPIs apis = mock(DecoraAPIs.class);
        when(apis.fetchPageFromSearchAPI(Mockito.anyString(), Mockito.any(), Mockito.eq(0)))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/decora/kipsplaat_search_api_response_1.txt"))));

        CompletableFuture<Document> stock1 = new CompletableFuture<>();
        stock1.complete(Jsoup.parseBodyFragment(Files.readString(Path.of("src/test/resources/decora/location-5952-kipsplaat-gkbi-niiskuskindlam-125x1200x2600.txt"))));
        when(apis.fetchLocationInfoForProduct(Mockito.eq("156")))
                .thenReturn(stock1);

        CompletableFuture<Document> stock2 = new CompletableFuture<>();
        stock2.complete(Jsoup.parseBodyFragment(Files.readString(Path.of("src/test/resources/decora/location-6015-kipsplaat-gn13-standard-125x1200x3300.txt"))));
        when(apis.fetchLocationInfoForProduct(Mockito.eq("131233")))
                .thenReturn(stock2);

        // actual testing
        GetDecoraProductsServiceImpl service = new GetDecoraProductsServiceImpl(apis);
        ProductsDto products = service.searchForProducts(query, null, null);

        assertFalse(products.getProducts().isEmpty(), "Product list should not be empty");
        assertEquals(EXPECTED_NUM_RESULTS, products.getProducts().size());

        // test that all stores are covered in locations
        assertEquals(DecoraStoreLocation.values().length, products.getProducts().getFirst().getStock().getLocations().size());
        assertEquals(DecoraStoreLocation.values().length, products.getProducts().getLast().getStock().getLocations().size());

        // test that loc field and quantity are present always
        for(ProductDto product : products.getProducts()) {
            assertNotNull(product.getStock(), "Product should always have stock attached to it");

            for(StockDto stock : product.getStock().getLocations()) {
                assertNotNull(stock.getLocation(), "Stock location should not be null");
                assertNotNull(stock.getQuantityText(), "Location quantity should not be null");
            }
        }

        // test that core fields are filled
        for(ProductDto product : products.getProducts()) {
            assertNotNull(product.getStore(), "Manually assign store to product");
            assertNotNull(product.getName(), "Product name should not be null");
            assertNotNull(product.getPrice(), "Product price should not be null");
            assertNotNull(product.getLinkToProduct(), "Product URL should not be null");
            assertNotNull(product.getLinkToPicture(), "Product URL should not be null");
            assertNotNull(product.getUnit(), "Product unit type should not be null");
        }
    }

    @Test
    public void testDecoraReturnsStockProperly() throws IOException {

        // mock prep
        final String query = "kipsplaat";
        final Map<String, String> expectedStock = Map.ofEntries(
                Map.entry("Tallinn, Laki põik 4", "389 tk"),
                Map.entry("Tartu, Riia 193", "Teadmata"),
                Map.entry("Pärnu, Suur-Jõe 57/1", "61 tk"),
                Map.entry("Võru, Lepa 2", "44 tk"),
                Map.entry("Viljandi, Leola 53", "116 tk"),
                Map.entry("Jõgeva, Puiestee 38", "21 tk"),
                Map.entry("Põltsamaa, Jõgeva mnt 23a", "Teadmata")
        );

        DecoraAPIs apis = mock(DecoraAPIs.class);
        when(apis.fetchPageFromSearchAPI(Mockito.any(), Mockito.any(), Mockito.eq(0)))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/decora/kipsplaat_search_api_response__product_5952.txt"))));

        CompletableFuture<Document> stock1 = new CompletableFuture<>();
        stock1.complete(Jsoup.parseBodyFragment(Files.readString(Path.of("src/test/resources/decora/location-5952-kipsplaat-gkbi-niiskuskindlam-125x1200x2600.txt"))));
        when(apis.fetchLocationInfoForProduct(Mockito.eq("156")))
                .thenReturn(stock1);

        // actual testing
        GetDecoraProductsServiceImpl service = new GetDecoraProductsServiceImpl(apis);
        ProductsDto products = service.searchForProducts("kipsplaat", null, null);

        for(ProductDto product : products.getProducts()) {
            for(StockDto stock : product.getStock().getLocations()) {
                assertTrue(expectedStock.containsKey(stock.getLocation().toString()), String.format("Location %s was not found in expected locations", stock.getLocation().toString()));
                assertEquals(expectedStock.get(stock.getLocation().toString()), stock.getQuantityText());
            }
        }
    }
}

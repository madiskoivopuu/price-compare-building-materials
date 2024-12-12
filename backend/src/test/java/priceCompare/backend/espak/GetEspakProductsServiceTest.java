package priceCompare.backend.espak;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.stores.espak.service.EspakAPIs;
import priceCompare.backend.stores.espak.service.GetEspakProductsService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetEspakProductsServiceTest {
    private CompletableFuture<Document> makeFutureForHTML(String htmlDocumentPath) throws IOException {
        CompletableFuture<Document> future = new CompletableFuture<>();
        Document doc = Jsoup.parse(Files.readString(Path.of(htmlDocumentPath)));

        future.complete(doc);
        return future;
    }

    @Test
    public void testEspakSearchByKeyword() throws IOException {
        final int EXPECTED_NUM_RESULTS = 2;
        final String keyword = "kruvikeeraja";

        EspakAPIs apis = mock(EspakAPIs.class);
        when(apis.fetchPageFromSearchAPI(Mockito.eq(keyword), Mockito.eq(null), Mockito.eq(0)))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/espak/kruvikeeraja_search_api_response_1.txt"))));

        when(apis.fetchProductPage(Mockito.eq("https://espak.ee/epood/toode/kruvikeeraja-otsak-t2057mm-flextorq-2tk-dt70533t/")))
                .thenReturn(makeFutureForHTML("src/test/resources/espak/product-kruvikeeraja-otsak-t2057mm-flextorq-2tk-dt70533t.txt"));
        when(apis.fetchProductPage(Mockito.eq("https://espak.ee/epood/toode/kruvikeeraja-otsak-t4057mm-flextorq-5tk-dt7399t/")))
                .thenReturn(makeFutureForHTML("src/test/resources/espak/product-kruvikeeraja-otsak-t4057mm-flextorq-5tk-dt7399t.txt"));

        GetEspakProductsService getEspakProductsService = new GetEspakProductsService(apis);
        ProductsDto products = getEspakProductsService.searchForProducts(keyword, null);

        assertFalse(products.getProducts().isEmpty(), "Product list should not be empty");
        assertEquals(EXPECTED_NUM_RESULTS, products.getProducts().size());
        for(ProductDto product : products.getProducts()) {
            assertNotNull(product.getStore(), "Store name should not be null");
            assertNotNull(product.getName(), "Product name should not be null");
            assertNotNull(product.getPrice(), "Product should have a price");
            assertNotNull(product.getUnit(), "Product should have a unit");
            assertNotNull(product.getStock(), "Product should have stock attached to it");
            assertNotNull(product.getLinkToProduct(), "Product should have link to the store");
            assertNotNull(product.getLinkToPicture(), "Product should have link to the image");
        }
    }
}

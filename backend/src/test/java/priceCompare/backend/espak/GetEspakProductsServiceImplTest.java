package priceCompare.backend.espak;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import priceCompare.backend.HttpClient.HttpClientService;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.stores.espak.service.GetEspakProductsServiceImpl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetEspakProductsServiceImplTest {
    private void mockResponseForGetStringAsync(HttpClientService httpClientService, String url, int responseCode, String responseBodyPath) throws IOException {
        CompletableFuture<HttpResponse<String>> future = new CompletableFuture<>();

        HttpResponse<String> response = mock(HttpResponse.class);
        when(response.statusCode()).thenReturn(responseCode);
        when(response.body()).thenReturn(Files.readString(Path.of(responseBodyPath)));

        future.complete(response);

        when(httpClientService.GetStringAsync(Mockito.eq(URI.create(url))))
                .thenReturn(future);
    }

    @Test
    public void testEspakSearchByKeyword() throws IOException {
        final int EXPECTED_NUM_RESULTS = 2;
        final String keyword = "kruvikeeraja";

        HttpClientService httpClientService = mock(HttpClientService.class);
        mockResponseForGetStringAsync(httpClientService, "https://espak.ee/epood/page/1/?s=kruvikeeraja&post_type=product", 200, "src/test/resources/espak/kruvikeeraja_search_api_response_1.txt");
        mockResponseForGetStringAsync(httpClientService, "https://espak.ee/epood/page/2/?s=kruvikeeraja&post_type=product", 404, "src/test/resources/espak/kruvikeeraja_search_api_response_2.txt");
        mockResponseForGetStringAsync(httpClientService, "https://espak.ee/epood/page/3/?s=kruvikeeraja&post_type=product", 404, "src/test/resources/espak/kruvikeeraja_search_api_response_2.txt");
        mockResponseForGetStringAsync(httpClientService, "https://espak.ee/epood/page/4/?s=kruvikeeraja&post_type=product", 404, "src/test/resources/espak/kruvikeeraja_search_api_response_2.txt");
        mockResponseForGetStringAsync(httpClientService, "https://espak.ee/epood/page/5/?s=kruvikeeraja&post_type=product", 404, "src/test/resources/espak/kruvikeeraja_search_api_response_2.txt");

        mockResponseForGetStringAsync(httpClientService, "https://espak.ee/epood/toode/otsik-lindikruvikeerajale-dewalt-ph2-pr2-156-mm-5tk/", 200, "src/test/resources/espak/product-otsik-lindikruvikeerajale-dewalt-ph2-pr2-156-mm-5tk.txt");
        mockResponseForGetStringAsync(httpClientService, "https://espak.ee/epood/toode/kruvikeeraja-hoidik-v2-intar/", 200, "src/test/resources/espak/product-kruvikeeraja-hoidik-v2-intar.txt");

        GetEspakProductsServiceImpl getEspakProductsService = new GetEspakProductsServiceImpl(httpClientService);
        ProductsDto products = getEspakProductsService.searchForProducts(keyword, null, null);

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

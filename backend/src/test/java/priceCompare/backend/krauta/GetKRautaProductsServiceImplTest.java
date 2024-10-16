package priceCompare.backend.krauta;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.stores.bauhof.service.GetBauhofProductsServiceImpl;
import priceCompare.backend.stores.krauta.service.GetKRautaProductsServiceImpl;
import priceCompare.backend.stores.krauta.service.KRautaAPIs;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;


@SpringBootTest
public class GetKRautaProductsServiceImplTest {
    @Test
    public void testRegularKrautaSearch() {
        String keyword = "kipsplaat";
        GetKRautaProductsServiceImpl getKRautaProductsService = new GetKRautaProductsServiceImpl(new KRautaAPIs());

        ProductsDto products = getKRautaProductsService.getKRautaProducts(keyword, null, null);

        assertNotNull(products, "ProductsDto should not be null");
        assertNotNull(products.getProducts(), "Product list should not be null");
        assertNotNull(products.getProducts().getFirst().getName(), "Product name should not be null");
        assertNotNull(products.getProducts().getFirst().getPrice(), "Product price should not be null");
        assertNotNull(products.getProducts().getFirst().getLinkToProduct(), "Product URL should not be null");
        assertNotNull(products.getProducts().getFirst().getUnit(), "Product unit type should not be null");
        assertFalse(products.getProducts().isEmpty(), "Product list should not be empty");

        assertTrue(products.getProducts().getFirst().getLinkToProduct().contains("https://k-rauta.ee/p/"), "Product URL should point to the k-rauta.ee domain");
    }

    @Test
    public void testSearchListsAllProducts() throws IOException, InterruptedException {
        String keyword = "knauf";
        final int numProductsInStock = 61;

        // set up api mocking
        KRautaAPIs apis = mock(KRautaAPIs.class);
        when(apis.fetchPageFromSearchAPI(Mockito.any(), Mockito.any(), Mockito.eq(0)))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/krauta_search_api_response_knauf_1.txt"))));
        when(apis.fetchPageFromSearchAPI(Mockito.any(), Mockito.any(), Mockito.eq(72)))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/krauta_search_api_response_knauf_2.txt"))));

        GetKRautaProductsServiceImpl getKRautaProductsService = new GetKRautaProductsServiceImpl(apis);
        ProductsDto products = getKRautaProductsService.getKRautaProducts(keyword, null, null);

        assertEquals(numProductsInStock, products.getProducts().size(), "K-rauta products service did not properly fetch the products that are in stock");
        verify(apis, times(2).description("K-rauta products service should have fetched products 2 times from the APIs")).fetchPageFromSearchAPI(Mockito.any(), Mockito.any(), Mockito.anyInt());
    }
}

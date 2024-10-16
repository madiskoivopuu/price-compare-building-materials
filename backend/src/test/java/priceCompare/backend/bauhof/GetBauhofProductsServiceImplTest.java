package priceCompare.backend.bauhof;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import priceCompare.backend.HttpClient.HttpClientService;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.stores.bauhof.service.GetBauhofProductsServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@SpringBootTest
public class GetBauhofProductsServiceImplTest {
    @Test
    public void testGetBauhofProducts() {
        String keyword = "kipsplaat";

        GetBauhofProductsServiceImpl getBauhofProductsService = new GetBauhofProductsServiceImpl(new HttpClientService());
        ProductsDto products = getBauhofProductsService.getBauhofProducts(keyword, null, null);

        System.out.println(products.getProducts());

        assertNotNull(products, "ProductsDto should not be null");
        assertNotNull(products.getProducts(), "Product list should not be null");
        assertFalse(products.getProducts().isEmpty(), "Product list should not be empty");

        assertNotNull(products.getProducts().getFirst().getName(), "Product name should not be null");
        assertNotNull(products.getProducts().getFirst().getPrice(), "Product price should not be null");
    }

    @Test
    public void testBauhofSearchListsAllProducts() throws IOException {
        String keyword = "kips";
        int numProductsInStock = 67;

        HttpClientService httpClientService = mock(HttpClientService.class);
        when(httpClientService.PostWithBody(Mockito.any(), Mockito.contains("\"offset\":0")))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/bauhof_search_api_response_kips_1.txt"))));
        when(httpClientService.PostWithBody(Mockito.any(), Mockito.contains("\"offset\":64")))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/bauhof_search_api_response_kips_2.txt"))));

        GetBauhofProductsServiceImpl getBauhofProductsService = new GetBauhofProductsServiceImpl(httpClientService);
        ProductsDto products = getBauhofProductsService.getBauhofProducts(keyword, null, null);

        assertEquals(numProductsInStock, products.getProducts().size(), "Bauhof products service did not properly fetch the products that are in stock");
        verify(httpClientService, times(2).description("Bauhof products service should have fetched products 2 times from the APIs")).PostWithBody(Mockito.any(), Mockito.any());
    }
}

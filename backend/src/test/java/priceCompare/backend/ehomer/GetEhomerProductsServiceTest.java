package priceCompare.backend.ehomer;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import priceCompare.backend.httpclient.HttpClientService;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.stores.ehomer.service.GetEhomerProductsService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class GetEhomerProductsServiceTest {

    @Mock
    private HttpClientService httpClientService;

    @InjectMocks
    private GetEhomerProductsService getEhomerProductsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchForProductsWithKeywordAndCategoryShouldReturnTwoProducts() throws IOException {
        String keyword = "kile";
        Subcategory subcategory = Subcategory.KILED;

        when(httpClientService.GetAndReturnJson(any()))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/ehomer/searchResponse.json"))));

        ProductsDto result = getEhomerProductsService.searchForProducts(keyword, subcategory);

        assertNotNull(result);
        assertEquals(2, result.getProducts().size());

        ProductDto product1 = result.getProducts().get(0);
        assertEquals("kile1", product1.getName());
        assertEquals(19.99, product1.getPrice());
        assertEquals("https://example.com/product1", product1.getLinkToProduct());
        assertEquals("https://example.com/image1.jpg", product1.getLinkToPicture());

        ProductDto product2 = result.getProducts().get(1);
        assertEquals("kile2", product2.getName());
        assertEquals(29.99, product2.getPrice());
        assertEquals("https://example.com/product2", product2.getLinkToProduct());
        assertEquals("https://example.com/image2.jpg", product2.getLinkToPicture());
    }

    @Test
    void testSearchForProductsWithKeywordOnlyShouldReturnTwoProducts() throws IOException {
        String keyword = "kile";

        when(httpClientService.GetAndReturnJson(any()))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/ehomer/searchResponse.json"))));
        ProductsDto result = getEhomerProductsService.searchForProducts(keyword, null);

        assertNotNull(result);
        assertEquals(2, result.getProducts().size());
    }

    @Test
    void testSearchForProductsWithCategoryOnlyShouldReturnTwoProducts() throws IOException {
        Subcategory subcategory = Subcategory.KILED;

        when(httpClientService.GetAndReturnJson(any()))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/ehomer/searchResponse.json"))));
        ProductsDto result = getEhomerProductsService.searchForProducts(null, subcategory);

        assertNotNull(result);
        assertEquals(2, result.getProducts().size());
    }

    @Test
    void testSearchForProducts_withEmptyResults() {
        String keyword = "NonExistingProduct";
        Subcategory subcategory = Subcategory.KANGAD;
        JSONObject mockedResponse = new JSONObject("{\"result\": []}");

        when(httpClientService.GetAndReturnJson(any())).thenReturn(mockedResponse);

        ProductsDto result = getEhomerProductsService.searchForProducts(keyword, subcategory);

        assertNotNull(result);
        assertTrue(result.getProducts().isEmpty());
    }

    @Test
    void testSearchForProducts_withInvalidJsonResponse() {
        String keyword = "Product";
        JSONObject mockedResponse = new JSONObject("{\"invalidKey\": []}");

        when(httpClientService.GetAndReturnJson(any())).thenReturn(mockedResponse);

        assertThrows(Exception.class, () -> {
            getEhomerProductsService.searchForProducts(keyword, null);
        });
    }
}

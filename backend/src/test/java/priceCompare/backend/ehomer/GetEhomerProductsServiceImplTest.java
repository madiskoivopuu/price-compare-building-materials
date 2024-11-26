package priceCompare.backend.ehomer;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import priceCompare.backend.HttpClient.HttpClientService;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.stores.ehomer.service.GetEhomerProductsServiceImpl;

class GetEhomerProductsServiceImplTest {

    @Mock
    private HttpClientService httpClientService;

    @InjectMocks
    private GetEhomerProductsServiceImpl getEhomerProductsService;

    private static final String SAMPLE_JSON_RESPONSE = """
        {
            "result": [
                {
                    "name": "Product 1",
                    "salePrice": 19.99,
                    "url": "https://example.com/product1",
                    "image": "https://example.com/image1.jpg"
                },
                {
                    "name": "Product 2",
                    "salePrice": 29.99,
                    "url": "https://example.com/product2",
                    "image": "https://example.com/image2.jpg"
                }
            ]
        }
    """;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchForProductsWithKeywordAndCategoryShouldReturnTwoProducts() {
        String keyword = "Product";
        Subcategory subcategory = Subcategory.KILED;
        JSONObject mockedResponse = new JSONObject(SAMPLE_JSON_RESPONSE);

        when(httpClientService.GetJson(any())).thenReturn(mockedResponse);

        ProductsDto result = getEhomerProductsService.searchForProducts(keyword, subcategory);

        assertNotNull(result);
        assertEquals(2, result.getProducts().size());

        ProductDto product1 = result.getProducts().get(0);
        assertEquals("Product 1", product1.getName());
        assertEquals(19.99, product1.getPrice());
        assertEquals("https://example.com/product1", product1.getLinkToProduct());
        assertEquals("https://example.com/image1.jpg", product1.getLinkToPicture());

        ProductDto product2 = result.getProducts().get(1);
        assertEquals("Product 2", product2.getName());
        assertEquals(29.99, product2.getPrice());
        assertEquals("https://example.com/product2", product2.getLinkToProduct());
        assertEquals("https://example.com/image2.jpg", product2.getLinkToPicture());
    }

    @Test
    void testSearchForProductsWithKeywordOnlyShouldReturnTwoProducts() {
        String keyword = "Product";
        JSONObject mockedResponse = new JSONObject(SAMPLE_JSON_RESPONSE);

        when(httpClientService.GetJson(any())).thenReturn(mockedResponse);

        ProductsDto result = getEhomerProductsService.searchForProducts(keyword, null);

        assertNotNull(result);
        assertEquals(2, result.getProducts().size());
    }

    @Test
    void testSearchForProductsWithCategoryOnlyShouldReturnTwoProducts() {
        Subcategory subcategory = Subcategory.KILED;
        JSONObject mockedResponse = new JSONObject(SAMPLE_JSON_RESPONSE);

        when(httpClientService.GetJson(any())).thenReturn(mockedResponse);

        ProductsDto result = getEhomerProductsService.searchForProducts(null, subcategory);

        assertNotNull(result);
        assertEquals(2, result.getProducts().size());
    }

    @Test
    void testSearchForProducts_withEmptyResults() {
        String keyword = "NonExistingProduct";
        Subcategory subcategory = Subcategory.KANGAD;
        JSONObject mockedResponse = new JSONObject("{\"result\": []}");

        when(httpClientService.GetJson(any())).thenReturn(mockedResponse);

        ProductsDto result = getEhomerProductsService.searchForProducts(keyword, subcategory);

        assertNotNull(result);
        assertTrue(result.getProducts().isEmpty());
    }

    @Test
    void testSearchForProducts_withInvalidJsonResponse() {
        String keyword = "Product";
        JSONObject mockedResponse = new JSONObject("{\"invalidKey\": []}");

        when(httpClientService.GetJson(any())).thenReturn(mockedResponse);

        assertThrows(Exception.class, () -> {
            getEhomerProductsService.searchForProducts(keyword, null);
        });
    }
}

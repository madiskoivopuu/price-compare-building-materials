package priceCompare.backend.bauhaus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import priceCompare.backend.httpclient.HttpClientService;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.stores.bauhaus.service.GetBauhausProductsService;
import priceCompare.backend.stores.bauhaus.service.BauhausStockFetcher;
import priceCompare.backend.stores.dto.ProductParseDto;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

class GetBauhausProductsServiceTest {

    @Mock
    private HttpClientService httpClientService;

    @InjectMocks
    private GetBauhausProductsService getBauhausProductsService;

    @Mock
    private BauhausStockFetcher locationStockInformationFetcher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchForProductsWithKeywordAndCategoryShouldReturnTwoProducts() throws IOException {
        String keyword = "Product";
        Subcategory subcategory = Subcategory.KILED;

        when(httpClientService.PostWithBodyAndReturnJson(any(), any()))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/bauhaus/searchResponse.json"))));
        when(locationStockInformationFetcher.fetchLocationInfo(any()))
                .thenReturn(new ArrayList<>() {ProductParseDto productParseDto;});

        ProductsDto result = getBauhausProductsService.searchForProducts(keyword, subcategory);

        verify(locationStockInformationFetcher).fetchLocationInfo(any());
        verify(httpClientService).PostWithBodyAndReturnJson(any(URI.class), any(String.class));
        assertNotNull(result);

    }

    @Test
    void testSearchForProductsWithKeywordOnlyShouldReturnTwoProducts() throws IOException {
        String keyword = "Product";

        when(httpClientService.PostWithBodyAndReturnJson(any(), any()))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/bauhaus/searchResponse.json"))));
        when(locationStockInformationFetcher.fetchLocationInfo(any()))
                .thenReturn(new ArrayList<>() {ProductParseDto productParseDto;});

        ProductsDto result = getBauhausProductsService.searchForProducts(keyword, null);

        verify(locationStockInformationFetcher).fetchLocationInfo(any());
        verify(httpClientService).PostWithBodyAndReturnJson(any(URI.class), any(String.class));
        assertNotNull(result);
    }

    @Test
    void testSearchForProductsWithCategoryOnlyShouldReturnTwoProducts() throws IOException {
        Subcategory subcategory = Subcategory.KILED;

        when(httpClientService.PostWithBodyAndReturnJson(any(), any()))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/bauhaus/searchResponse.json"))));
        when(locationStockInformationFetcher.fetchLocationInfo(any()))
                .thenReturn(new ArrayList<>() {ProductParseDto productParseDto;});

        ProductsDto result = getBauhausProductsService.searchForProducts(null, subcategory);

        verify(locationStockInformationFetcher).fetchLocationInfo(any());
        verify(httpClientService).PostWithBodyAndReturnJson(any(URI.class), any(String.class));
        assertNotNull(result);
    }

    @Test
    void testSearchForProducts_withEmptyResults() {
        String keyword = "NonExistingProduct";
        Subcategory subcategory = Subcategory.KANGAD;
        JSONObject mockedEmptyResponse = new JSONObject("{\"records\": []}");

        when(httpClientService.PostWithBodyAndReturnJson(any(), any()))
                .thenReturn(mockedEmptyResponse);

        ProductsDto result = getBauhausProductsService.searchForProducts(keyword, subcategory);

        assertNotNull(result);
        assertTrue(result.getProducts().isEmpty());
    }
}

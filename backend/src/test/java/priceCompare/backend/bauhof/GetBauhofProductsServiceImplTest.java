package priceCompare.backend.bauhof;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import priceCompare.backend.HttpClient.HttpClientService;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.stores.bauhof.service.BauhofApis;
import priceCompare.backend.stores.bauhof.service.GetBauhofProductsServiceImpl;
import priceCompare.backend.stores.bauhof.service.LocationStockInformationFetcherBauhof;
import priceCompare.backend.stores.dto.ProductParseDto;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetBauhofProductsServiceImplTest {
    @Mock
    private HttpClientService httpClientService;

    @InjectMocks
    private GetBauhofProductsServiceImpl getBauhofProductsService;

    @Mock
    private LocationStockInformationFetcherBauhof locationStockInformationFetcher;

    @Mock
    private BauhofApis apis;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(apis.buildUrl(any())).thenReturn(URI.create("https://www.bauhof.ee/api/klevu/search"));
    }

    @Test
    void testSearchForProductsWithKeywordAndCategory() throws IOException, JSONException {
        String keyword = "kipsplaat";
        Subcategory subcategory = Subcategory.KIPSPLAAT;

        when(httpClientService.PostWithBodyAndReturnJson(any(), any()))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/bauhof/bauhof_search_api_response_kips_1.json"))));
        when(locationStockInformationFetcher.fetchLocationInfo(any())).thenReturn(List.of(ProductParseDto.builder().build()));
        when(apis.buildRequestBody("kipsplaat", 0, 64)).thenReturn("body");

        ProductsDto result = getBauhofProductsService.searchForProducts(keyword, subcategory);

        verify(locationStockInformationFetcher).fetchLocationInfo(any());
        verify(httpClientService).PostWithBodyAndReturnJson(any(URI.class), any(String.class));
        assertNotNull(result);
    }

    @Test
    void testSearchForProductsWithKeyword() throws IOException, JSONException {
        String keyword = "kipsplaat";

        when(httpClientService.PostWithBodyAndReturnJson(any(), any()))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/bauhof/bauhof_search_api_response_kips_1.json"))));
        when(locationStockInformationFetcher.fetchLocationInfo(any())).thenReturn(List.of(ProductParseDto.builder().build()));
        when(apis.buildRequestBody("kipsplaat", 0, 64)).thenReturn("body");

        ProductsDto result = getBauhofProductsService.searchForProducts(keyword, null);

        verify(locationStockInformationFetcher).fetchLocationInfo(any());
        verify(httpClientService).PostWithBodyAndReturnJson(any(URI.class), any(String.class));
        assertNotNull(result);
    }

    @Test
    void testSearchForProductsWithCategory() throws IOException, JSONException {
        Subcategory subcategory = Subcategory.KIPSPLAAT;

        when(httpClientService.PostWithBodyAndReturnJson(any(), any()))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/bauhof/bauhof_search_api_response_kips_1.json"))));
        when(locationStockInformationFetcher.fetchLocationInfo(any())).thenReturn(List.of(ProductParseDto.builder().build()));
        when(apis.buildRequestBody("kipsplaat", 0, 64)).thenReturn("body");

        ProductsDto result = getBauhofProductsService.searchForProducts(null, subcategory);

        verify(locationStockInformationFetcher).fetchLocationInfo(any());
        verify(httpClientService).PostWithBodyAndReturnJson(any(URI.class), any(String.class));
        assertNotNull(result);
    }

    @Test
    void testSearchForProducts_withEmptyResults() throws IOException {
        String keyword = "NonExistingProduct";
        Subcategory subcategory = Subcategory.KANGAD;

        when(httpClientService.PostWithBodyAndReturnJson(any(), any()))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/bauhof/bauhof_search_api_response_kips_empty.json"))));

        ProductsDto result = getBauhofProductsService.searchForProducts(keyword, subcategory);

        assertNotNull(result);
        assertTrue(result.getProducts().isEmpty());
    }
}

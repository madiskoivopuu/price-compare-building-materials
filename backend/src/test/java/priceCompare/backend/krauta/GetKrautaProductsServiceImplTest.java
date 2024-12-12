package priceCompare.backend.krauta;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import priceCompare.backend.httpclient.HttpClientService;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.stores.dto.ProductParseDto;
import priceCompare.backend.stores.krauta.service.GetKRautaProductsService;
import priceCompare.backend.stores.krauta.service.KRautaAPIs;
import priceCompare.backend.stores.krauta.service.KrautaStockFetcher;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetKrautaProductsServiceImplTest {
    @Mock
    private HttpClientService httpClientService;

    @InjectMocks
    private GetKRautaProductsService getKrautaProductsService;

    @Mock
    private KrautaStockFetcher locationStockInformationFetcher;

    @Mock
    private KRautaAPIs kRautaAPIs;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchForProductsWithKeywordAndCategory() throws IOException, JSONException {
        String keyword = "kipsplaat";
        Subcategory subcategory = Subcategory.KIPSPLAAT;

        when(kRautaAPIs.fetchPageFromSearchAPI(keyword, subcategory, 0))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/krauta/krauta_search_api_response_knauf_1.json"))));
        when(locationStockInformationFetcher.fetchLocationStockInfo(any())).thenReturn(List.of(ProductParseDto.builder().build()));

        ProductsDto result = getKrautaProductsService.searchForProducts(keyword, subcategory);

        verify(locationStockInformationFetcher).fetchLocationStockInfo(any());
        verify(kRautaAPIs).fetchPageFromSearchAPI(keyword, subcategory, 0);
        assertNotNull(result);
    }

    @Test
    void testSearchForProductsWithKeyword() throws IOException, JSONException {
        String keyword = "kipsplaat";

        when(kRautaAPIs.fetchPageFromSearchAPI(keyword, null, 0))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/krauta/krauta_search_api_response_knauf_1.json"))));
        when(locationStockInformationFetcher.fetchLocationStockInfo(any())).thenReturn(List.of(ProductParseDto.builder().build()));

        ProductsDto result = getKrautaProductsService.searchForProducts(keyword, null);

        verify(locationStockInformationFetcher).fetchLocationStockInfo(any());
        verify(kRautaAPIs).fetchPageFromSearchAPI(keyword, null, 0);
        assertNotNull(result);
    }

    @Test
    void testSearchForProductsWithCategory() throws IOException, JSONException {
        Subcategory subcategory = Subcategory.KIPSPLAAT;

        when(kRautaAPIs.fetchPageFromSearchAPI("kipsplaat", subcategory, 0))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/krauta/krauta_search_api_response_knauf_1.json"))));
        when(locationStockInformationFetcher.fetchLocationStockInfo(any())).thenReturn(List.of(ProductParseDto.builder().build()));

        ProductsDto result = getKrautaProductsService.searchForProducts(null, subcategory);

        verify(locationStockInformationFetcher).fetchLocationStockInfo(any());
        verify(kRautaAPIs).fetchPageFromSearchAPI("kipsplaat", subcategory, 0);
        assertNotNull(result);
    }

    @Test
    void testSearchForProducts_withEmptyResults() throws IOException {
        String keyword = "NonExistingProduct";
        Subcategory subcategory = Subcategory.KANGAD;

        when(kRautaAPIs.fetchPageFromSearchAPI(keyword, subcategory, 0))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/krauta/krauta_search_api_response_knauf_empty.json"))));

        ProductsDto result = getKrautaProductsService.searchForProducts(keyword, subcategory);

        assertNotNull(result);
        assertTrue(result.getProducts().isEmpty());
    }
}

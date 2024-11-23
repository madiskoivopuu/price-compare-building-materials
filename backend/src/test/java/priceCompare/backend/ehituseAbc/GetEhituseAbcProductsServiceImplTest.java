package priceCompare.backend.ehituseAbc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import priceCompare.backend.HttpClient.HttpClientService;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.enums.Unit;
import priceCompare.backend.stores.ehituseabc.service.EhituseAbcApis;
import priceCompare.backend.stores.ehituseabc.service.GetEhituseAbcProductsServiceImpl;
import priceCompare.backend.stores.ehituseabc.service.LocationStockInformationFetcherEhituseAbc;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

class GetEhituseAbcProductsServiceImplTest {

    @Mock
    private HttpClientService httpClientService;

    @Mock
    private LocationStockInformationFetcherEhituseAbc locationStockInformationFetcher;

    @Mock
    private EhituseAbcApis apis;

    @InjectMocks
    private GetEhituseAbcProductsServiceImpl getEhituseAbcProductsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchForProductsWithKeywordAndCategoryReturnsCorrectlyParsedProducts() throws IOException {
        String keyword = "kipsplaat";
        Subcategory subcategory = Subcategory.KIPSPLAAT;
        when(httpClientService.PostWithBody(any(URI.class), any(String.class)))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/ehituseAbc/searchForKipsplaatResponse.json"))));
        ProductsDto result = getEhituseAbcProductsService.searchForProducts(keyword, subcategory);

        verify(locationStockInformationFetcher).fetchLocationInfo(any());
        verify(httpClientService).PostWithBody(any(URI.class), any(String.class));
        assertNotNull(result);
    }

    @Test
    void searchForProductsWithOnlyKeywordReturnsProducts() throws IOException {
        String keyword = "kipsplaat";
        when(httpClientService.PostWithBody(any(URI.class), any(String.class)))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/ehituseAbc/searchForKipsplaatResponse.json"))));

        ProductsDto result = getEhituseAbcProductsService.searchForProducts(keyword, null);

        verify(locationStockInformationFetcher).fetchLocationInfo(any());
        verify(httpClientService).PostWithBody(any(URI.class), any(String.class));
        assertNotNull(result);
    }

    @Test
    void getProductsByCategoryWithValidCategoryReturnsProducts() throws IOException {
        Subcategory subcategory = Subcategory.KIPSPLAAT;

        when(httpClientService.PostWithBody(any(URI.class), any(String.class)))
                .thenReturn(new JSONObject(Files.readString(Path.of("src/test/resources/ehituseAbc/searchForKipsplaatResponse.json"))));

        ProductsDto result = getEhituseAbcProductsService.searchForProducts(null, subcategory);

        verify(locationStockInformationFetcher).fetchLocationInfo(any());
        verify(httpClientService, times(5)).PostWithBody(any(URI.class), any(String.class));
        assertNotNull(result);
    }


    @Test
    void extractUnit_withValidAdditionalData_returnsUnit() {
        String additionalData = "{ \"priceUnit\": \"tk\" }";

        Unit result = getEhituseAbcProductsService.extractUnit(additionalData);

        assertEquals(Unit.TK, result);
    }
}

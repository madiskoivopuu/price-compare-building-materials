package priceCompare.backend.bauhof;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import priceCompare.backend.HttpClient.HttpClientService;
import priceCompare.backend.dto.StockInLocationsDto;
import priceCompare.backend.enums.LocationName;
import priceCompare.backend.stores.bauhof.service.LocationStockInformationFetcherBauhof;
import java.net.URI;

class LocationStockInformationFetcherBauhofTest {

    @Mock
    private HttpClientService httpClientService;

    @InjectMocks
    private LocationStockInformationFetcherBauhof stockInformationFetcher;

    JSONObject mockResponse;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockResponse = new JSONObject(
                "{ \"data\": { " +
                        "\"products\": { " +
                        "\"items\": [ { " +
                        "\"availability_in_stores\": [ " +
                        "{ \"qty\": 154, \"source_name\": \"Haapsalu Bauhof\", \"status\": 1 }, " +
                        "{ \"qty\": 168, \"source_name\": \"Jõhvi Bauhof\", \"status\": 1 }, " +
                        "{ \"qty\": 0, \"source_name\": \"Default Source\", \"status\": 0 }, " +
                        "{ \"qty\": 321, \"source_name\": \"Tartu Bauhof\", \"status\": 1 } " +
                        "] " +
                        "} ] " +
                        "} " +
                        "} }"
        );
    }

    @Test
    void getLocationAndStockInformation_ShouldReturnStockInLocationsDto() {
        String sku = "123456";

        when(httpClientService.PostWithBody(any(URI.class), any(String.class))).thenReturn(mockResponse);

        StockInLocationsDto result = stockInformationFetcher.getLocationAndStockInformation(sku);

        verify(httpClientService).PostWithBody(any(URI.class), any(String.class));

        assertNotNull(result);
        assertEquals(13, result.getLocations().size());
    }

    @Test
    void parseResponse_ShouldParseAndReturnStockList() {

        StockInLocationsDto result = stockInformationFetcher.parseResponse(mockResponse);

        assertEquals(13, result.getLocations().size());

        assertEquals(LocationName.HAAPSALU, result.getLocations().get(0).getLocation().getLocationName());
        assertEquals("154 tk", result.getLocations().get(0).getQuantityText());

        assertEquals(LocationName.JOHVI, result.getLocations().get(1).getLocation().getLocationName());
        assertEquals("168 tk", result.getLocations().get(1).getQuantityText());

        assertEquals(LocationName.TARTU, result.getLocations().get(2).getLocation().getLocationName());
        assertEquals("321 tk", result.getLocations().get(2).getQuantityText());
    }

    @Test
    void buildRequestPayload_ShouldFormatRequestPayloadWithSku() {
        String sku = "123456";
        String payload = stockInformationFetcher.buildRequestPayload(sku);

        assertNotNull(payload);
        assertTrue(payload.contains("\"sku\":{\"eq\":\"123456\""));
    }
}

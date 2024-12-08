package priceCompare.backend.espak;

import org.junit.jupiter.api.Test;
import priceCompare.backend.HttpClient.CachingHttpClientService;
import priceCompare.backend.HttpClient.HttpClientService;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.service.SearchCachingService;
import priceCompare.backend.stores.espak.service.EspakAPIs;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EspakAPIsTest {
    @Test
    public void testFiltersFormattedCorrectly_NoCategories() {
        final String expected = String.format("[\"stock_status:in-stock\", \"taxonomies.product_cat:%s\"]", EspakAPIs.NO_CATEGORY);

        EspakAPIs apis = new EspakAPIs(new CachingHttpClientService());
        String res = apis.formatFacetFilters(Subcategory.LAKKAPLOKK);

        assertEquals(expected, res);
    }

    @Test
    public void testFiltersFormattedCorrectly_MultipleCategories() {
        final String expected = "[\"stock_status:in-stock\", [\"taxonomies.product_cat:keramsiitplokid\", \"taxonomies.product_cat:keramsiitplokid-fibo\"]]";

        EspakAPIs apis = new EspakAPIs(new CachingHttpClientService());
        String res = apis.formatFacetFilters(Subcategory.FIBO);

        assertEquals(expected, res);
    }
}

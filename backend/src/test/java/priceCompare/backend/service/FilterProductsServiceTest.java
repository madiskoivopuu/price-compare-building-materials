package priceCompare.backend.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Keyword;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FilterProductsServiceTest {
    private FilterProductsService filterProductsService;

    @Mock
    private SearchCachingService cachingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        filterProductsService = new FilterProductsService(cachingService);
    }

    @Test
    void testMatchesAlias() {
        ProductDto product = ProductDto.builder().name("TestProduct").build();

        assertTrue(filterProductsService.matchesAlias(product, "test"));

        assertFalse(filterProductsService.matchesAlias(product, "other"));

        assertTrue(filterProductsService.matchesAlias(product, "TEST"));
    }

    @Test
    void testProductMatchesFilters_withNoAliasMatching() {
        ProductDto product = ProductDto.builder().name("kipsplaat").build();

        Keyword keyword1 = mockKeyword("osb", false);

        List<List<Keyword>> groupedFilters = Collections.singletonList(
                Collections.singletonList(keyword1)
        );

        assertFalse(filterProductsService.productMatchesFilters(product, groupedFilters));
    }

    @Test
    void testProductMatchesFilters_withCheckThatDoesNotContainAnyKeywordsInList() {
        ProductDto product = ProductDto.builder().name("kipsplaat").build();

        Keyword keyword = mockKeyword("osb", true);

        List<List<Keyword>> groupedFilters = Collections.singletonList(
                Collections.singletonList(keyword)
        );

        assertTrue(filterProductsService.productMatchesFilters(product, groupedFilters));
    }

    @Test
    void testProductDoesNotMatchFilters_withCheckThatDoesNotContainAnyKeywordsInList() {
        ProductDto product = ProductDto.builder().name("kipsplaat").build();

        Keyword keyword = mockKeyword("kips", true);

        List<List<Keyword>> groupedFilters = Collections.singletonList(
                Collections.singletonList(keyword)
        );

        assertFalse(filterProductsService.productMatchesFilters(product, groupedFilters));
    }

    @Test
    void testFilter_withValidCacheAndFilters() {
        String searchId = "testId";

        ProductDto product1 = ProductDto.builder().name("kipsplaat").build();
        ProductDto product2 = ProductDto.builder().name("osb-plaat").build();
        ProductsDto cachedProducts = ProductsDto.builder()
                .products(Arrays.asList(product1, product2))
                .build();

        when(cachingService.getSearchResultFromCache(searchId)).thenReturn(cachedProducts);

        Keyword keyword = mockKeyword("kipsplaat", false);
        List<List<Keyword>> groupedFilters = Collections.singletonList(
                Collections.singletonList(keyword)
        );

        ProductsDto result = filterProductsService.filter(searchId, groupedFilters);

        assertNotNull(result);
        assertEquals(1, result.getProducts().size());
        assertEquals("kipsplaat", result.getProducts().get(0).getName());

        verify(cachingService, times(1)).getSearchResultFromCache(searchId);
    }

    private Keyword mockKeyword(String alias, boolean checkThatDoesNotContainAnyKeywordsInList) {
        Keyword keyword = mock(Keyword.class);
        when(keyword.getAliases()).thenReturn(Collections.singletonList(alias));
        when(keyword.isCheckThatDoesNotContainAnyKeywordsInList()).thenReturn(checkThatDoesNotContainAnyKeywordsInList);
        return keyword;
    }
}
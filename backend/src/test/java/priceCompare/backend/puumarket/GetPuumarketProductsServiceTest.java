package priceCompare.backend.puumarket;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import priceCompare.backend.httpclient.HttpClientService;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.stores.puumarket.service.EmaterjalToPuumarketCategoryMapping;
import priceCompare.backend.stores.puumarket.service.GetPuumarketProductsService;
import priceCompare.backend.stores.puumarket.service.PuumarketStockFetcher;
import priceCompare.backend.stores.puumarket.service.PuumarketAPIs;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class GetPuumarketProductsServiceTest {
    @Test
    void testSearchByKeyword() throws IOException {
        final int EXPECTED_NUM_RESULTS = 26; // only 26 products contained keyword
        final String keyword = "puu";

        PuumarketAPIs apis = mock(PuumarketAPIs.class);
        when(apis.performSearchOrCategoryPageFetch(Mockito.eq(keyword), Mockito.eq(null)))
            .thenReturn(List.of(Jsoup.parse(Files.readString(Path.of("src/test/resources/puumarket/keyword_puu_search_resp.txt")))));

        GetPuumarketProductsService getPuumarketProductsService = new GetPuumarketProductsService(apis, new PuumarketStockFetcher());
        ProductsDto products = getPuumarketProductsService.searchForProducts(keyword, null);

        assertFalse(products.getProducts().isEmpty(), "Product list should not be empty");
        assertEquals(EXPECTED_NUM_RESULTS, products.getProducts().size());
        for(ProductDto product : products.getProducts()) {
            assertNotNull(product.getStore(), "Store name should not be null");
            assertNotNull(product.getName(), "Product name should not be null");
            assertNotNull(product.getPrice(), "Product should have a price");
            assertNotNull(product.getUnit(), "Product should have a unit");
            assertNotNull(product.getStock(), "Product should have stock attached to it");
            assertNotNull(product.getLinkToProduct(), "Product should have link to the store");
            assertNotNull(product.getLinkToPicture(), "Product should have link to the image");
        }
    }

    @Test
    void testCategorySearches() throws IOException {
        for(Category cat : Category.values()) {
            for(Subcategory subcategory : cat.getSubcategories()) {
                // exhaustive test - check that all categories are fetched
                final List<String> visitablePages = EmaterjalToPuumarketCategoryMapping.categoryMap.get(subcategory);
                final int EXPECTED_PAGE_VISITS = visitablePages.size();

                HttpClientService httpClientService = mock(HttpClientService.class);
                for(String pageLink : visitablePages) {
                    CompletableFuture<HttpResponse<String>> future = CompletableFuture.supplyAsync(() -> {
                        throw new RuntimeException("test");
                    });
                    when(httpClientService.GetAsyncAndReturnCompletableFutureHttpResponse(Mockito.eq(URI.create(pageLink))))
                            .thenReturn(future);
                }

                GetPuumarketProductsService getPuumarketProductsService = new GetPuumarketProductsService(new PuumarketAPIs(httpClientService), new PuumarketStockFetcher());
                getPuumarketProductsService.searchForProducts("", subcategory);
                verify(httpClientService, times(EXPECTED_PAGE_VISITS)).GetAsyncAndReturnCompletableFutureHttpResponse(Mockito.any());

            }
        }
    }
}

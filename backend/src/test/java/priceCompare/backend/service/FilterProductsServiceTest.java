package priceCompare.backend.service;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Keyword;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FilterProductsServiceTest {
    /*
    @Mock
    private SearchCachingService cachingService;
    @Mock
    private CachingHttpClientService cachingHttpClientService;
    @InjectMocks
    private FilterProductsService filterProductsService;

    static Map<String, String> productPages;
    static List<ProductDto> productDtoList;

    @BeforeAll
    static void setup() throws IOException {
        productPages = new HashMap<>();
        productDtoList = new ArrayList<>();

        productPages.put(
                "https://www.bauhof.ee/et/p/713218/kipsplaat-knauf-gkb-12-5x1200x2600mm-standard-3-12m2-tk",
                Files.readString(Path.of("src/test/resources/service/filterproducts/page-bauhof.ee_et_p_713218_kipsplaat-knauf-gkb-12-5x1200x2600mm-standard-3-12m2-tk.txt"))
        );
        productPages.put(
                "https://www.ehituseabc.ee/ee/kipsplaat-knauf-gkb13-2500x1200",
                Files.readString(Path.of("src/test/resources/service/filterproducts/page-ehituseabc.ee_ee_kipsplaat-knauf-gkb13-2500x1200.txt"))
        );
        productPages.put(
                "https://www.k-rauta.ee/p/kipsplaat-norgips-260-cm-x-120-cm-x-1-3-cm-tulekindel/sxnu",
                Files.readString(Path.of("src/test/resources/service/filterproducts/page-k-rauta.ee_p_kipsplaat-norgips-260-cm-x-120-cm-x-1-3-cm-tulekindel_sxnu.txt"))
        );
        productPages.put(
                "https://www.k-rauta.ee/p/kipsplaat-300-cm-x-120-cm-x-1-3-cm/1ez4",
                Files.readString(Path.of("src/test/resources/service/filterproducts/page-k-rauta.ee_p_kipsplaat-300-cm-x-120-cm-x-1-3-cm_1ez4.txt"))
        );

        productDtoList.add(ProductDto.builder()
                .name("KIPSPLAAT KNAUF GKB 12,5X1200X2600MM STANDARD 3,12M2/TK")
                .linkToProduct("https://www.bauhof.ee/et/p/713218/kipsplaat-knauf-gkb-12-5x1200x2600mm-standard-3-12m2-tk")
                .build()
        );
        productDtoList.add(
                ProductDto.builder()
                        .name("KIPSPLAAT KNAUF GKB13 2500X1200")
                        .linkToProduct("https://www.ehituseabc.ee/ee/kipsplaat-knauf-gkb13-2500x1200")
                        .build()
        );
        productDtoList.add(
                ProductDto.builder()
                        .name("Kipsplaat Norgips, 260 cm x 120 cm x 1.3 cm, tulekindel")
                        .linkToProduct("https://www.k-rauta.ee/p/kipsplaat-norgips-260-cm-x-120-cm-x-1-3-cm-tulekindel/sxnu")
                        .build()
        );
        productDtoList.add(
                ProductDto.builder()
                        .name("Kipsplaat, 300 cm x 120 cm x 1.3 cm")
                        .linkToProduct("https://www.k-rauta.ee/p/kipsplaat-300-cm-x-120-cm-x-1-3-cm/1ez4")
                        .build()
        );
    }

    CompletableFuture<HttpResponse<String>> makeResponse(String pageContent) {
        HttpResponse<String> resp = mock(HttpResponse.class);
        when(resp.body())
                .thenReturn(pageContent);

        CompletableFuture<HttpResponse<String>> future = new CompletableFuture<>();
        future.complete(resp);
        return future;
    }

    @BeforeEach
    public void setupTest() {
        for(Map.Entry<String, String> urlAndPage : productPages.entrySet()) {
            CompletableFuture<HttpResponse<String>> future = makeResponse(urlAndPage.getValue());
            when(cachingHttpClientService.GetAsyncCached(eq(urlAndPage.getKey())))
                    .thenReturn(future);
        }

        when(cachingService.getSearchResultFromCache(any()))
                .thenReturn(ProductsDto.builder()
                        .products(productDtoList)
                        .build()
                );
    }

    @Test
    public void testFilteringBySize() {
        // prep
        final List<List<Keyword>> filters = List.of(
            List.of(Keyword.D1200x3000, Keyword.D1200x2600)
        ); // filters in the same filter category should be under the same 1d list
        final Set<ProductDto> expectedResults = Set.of(productDtoList.get(0), productDtoList.get(2), productDtoList.get(3));
        // test
        ProductsDto filtered = filterProductsService.filter("", filters);
        assertEquals(expectedResults.size(), filtered.getProducts().size());

        for(ProductDto product : filtered.getProducts())
            assertTrue(expectedResults.contains(product), String.format("Product %s should pass the filters", product.getName()));
    }

    @Test
    public void testFilteringByType() {
        // prep
        final List<List<Keyword>> filters = List.of(
                List.of(Keyword.STANDARD)
        ); // filters in the same filter category should be under the same 1d list
        final Set<ProductDto> expectedResults = Set.of(productDtoList.get(0), productDtoList.get(1));

        // test
        ProductsDto filtered = filterProductsService.filter("", filters);
        assertEquals(expectedResults.size(), filtered.getProducts().size());

        for(ProductDto product : filtered.getProducts())
            assertTrue(expectedResults.contains(product), String.format("Product %s should pass the filters", product.getName()));
    }

    @Test
    public void testFilteringImpossibleFilter() {
        // prep
        final List<List<Keyword>> filters = List.of(
                List.of(Keyword.VINEER_VEEKINDEL)
        ); // filters in the same filter category should be under the same 1d list

        // test
        ProductsDto filtered = filterProductsService.filter("", filters);
        assertEquals(0, filtered.getProducts().size());
    }

    @Test
    public void testFiltersFromMultipleFilterCategories() {
        // prep
        final List<List<Keyword>> filters = List.of(
                List.of(Keyword.STANDARD),
                List.of(Keyword.D1200x3000, Keyword.D1200x2600)
        ); // filters in the same filter category should be under the same 1d list
        final Set<ProductDto> expectedResults = Set.of(productDtoList.get(0));

        // test
        ProductsDto filtered = filterProductsService.filter("", filters);
        assertEquals(expectedResults.size(), filtered.getProducts().size());

        for(ProductDto product : filtered.getProducts())
            assertTrue(expectedResults.contains(product), String.format("Product %s should pass the filters", product.getName()));
    }
    */

}

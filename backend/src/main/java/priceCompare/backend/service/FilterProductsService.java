package priceCompare.backend.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Keyword;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterProductsService {
    private static final Logger logger = LogManager.getLogger(FilterProductsService.class);

    private final SearchCachingService cachingService;

    private boolean productMatchesFilters(ProductDto product, List<List<Keyword>> selectedGroupedFilters) {
        int filterGroupsMatched = 0;

        for (List<Keyword> filtersInGroup : selectedGroupedFilters) {
            boolean groupMatches = false;

            for (Keyword keyword : filtersInGroup) {
                if (keyword.isCheckThatDoesNotContainAnyKeywordsInList()) {
                    // Check that none of the aliases match
                    boolean aliasMatches = keyword.getAliases().stream()
                            .anyMatch(alias -> matchesAlias(product, alias));
                    if (!aliasMatches) {
                        groupMatches = true;
                        break; // No need to check further if the group matches
                    }
                } else {
                    // Check that at least one alias matches
                    boolean aliasMatches = keyword.getAliases().stream()
                            .anyMatch(alias -> matchesAlias(product, alias));
                    if (aliasMatches) {
                        groupMatches = true;
                        break; // No need to check further if the group matches
                    }
                }
            }
            if (groupMatches) {
                filterGroupsMatched++;
            }
        }

        return filterGroupsMatched == selectedGroupedFilters.size();
    }

    private boolean matchesAlias(ProductDto product, String alias) {
        return product.getName().toLowerCase().contains(alias.toLowerCase());
    }

    public ProductsDto filter(String searchId, List<List<Keyword>> selectedGroupedFilters) {
        System.out.println(selectedGroupedFilters);
        ProductsDto cachedProducts = cachingService.getSearchResultFromCache(searchId);
        if(cachedProducts == null)
            return ProductsDto.builder().build();

        List<ProductDto> filteredProducts = new ArrayList<>();

        long start = System.currentTimeMillis();
        cachedProducts.getProducts().stream()
                .parallel()
                .filter(products -> productMatchesFilters(products, selectedGroupedFilters))
                .forEach(filteredProducts::add);

        long end = System.currentTimeMillis();
        logger.info(String.format("Filtering API performance: took %dms to filter %d products%n", (end-start), cachedProducts.getProducts().size()));

        return ProductsDto.builder()
                .products(filteredProducts)
                .build();
    }
}

package priceCompare.backend.controller;

import static org.springframework.http.HttpStatus.OK;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import priceCompare.backend.dto.CategoriesDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Keyword;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.service.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/request")
public class Controller {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private FindProductsServiceImpl findProductsService;

    @Autowired
    private ProxyImageLoadingService proxyImageLoadingService;

    @Autowired
    private FilterProductsService filterProductsService;

    @Autowired
    private SearchCachingService cachingService;

    @GetMapping("/categories")
    @ResponseStatus(OK)

    public ResponseEntity<CategoriesDto> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/search")
    @ResponseStatus(OK)
    public ResponseEntity<ProductsDto> searchProducts(
            @RequestParam() String keyword,
            @RequestParam(required = false) Subcategory subcategory) {

        ProductsDto products = findProductsService.findProducts(keyword, subcategory);
        System.out.println(products.getSearchId());
        return ResponseEntity.ok(products);
    }

    @RequestMapping("/proxy-img-req")
    @ResponseStatus(OK)
    public ResponseEntity<InputStreamResource> proxyImageLoading(
            @RequestParam() String link
    ) {
        return proxyImageLoadingService.loadImage(link);
    }

    @RequestMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductsDto> filterProducts(
            @RequestParam String searchId,
            @RequestParam(required = false) String filters // Receive as a raw String
    ) {
        if (filters==null || filters.isEmpty()) {
            return ResponseEntity.ok(cachingService.getSearchResultFromCache(searchId));
        }
        // Split groups by '_' and then each group by ','
        List<List<Keyword>> parsedFilters = Arrays.stream(filters.split("_"))
                .map(group -> Arrays.stream(group.split(";"))
                        .map(Keyword::fromDisplayName)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        System.out.println(parsedFilters);

        ProductsDto filteredProducts = filterProductsService.filter(searchId, parsedFilters);
        return ResponseEntity.ok(filteredProducts);
    }
}

package priceCompare.backend.controller;

import static org.springframework.http.HttpStatus.OK;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import priceCompare.backend.dto.CategoriesDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.service.CategoryServiceImpl;
import priceCompare.backend.service.FindProductsServiceImpl;
import priceCompare.backend.service.ProxyImageLoadingService;

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

        return ResponseEntity.ok(products);
    }

    @RequestMapping("/proxy-img-req")
    @ResponseStatus(OK)
    public ResponseEntity<InputStreamResource> proxyImageLoading(
            @RequestParam() String link
    ) {
        return proxyImageLoadingService.loadImage(link);
    }
}

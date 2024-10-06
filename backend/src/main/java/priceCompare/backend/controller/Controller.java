package priceCompare.backend.controller;

import static org.springframework.http.HttpStatus.OK;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import priceCompare.backend.dto.CategoriesDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.service.CategoryServiceImpl;
import priceCompare.backend.service.FindProductsServiceImpl;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/request")
public class Controller {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private FindProductsServiceImpl findProductsService;

    @GetMapping("/categories")
    @ResponseStatus(OK)

    public ResponseEntity<CategoriesDto> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/search")
    @ResponseStatus(OK)
    public ResponseEntity<ProductsDto> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) List<String> location,
            @RequestParam(required = false) Integer minQuantity,
            //todo find proper way to include additional filters in request, maybe keep it in FE
            @RequestParam Map<String, String> allParams) {


        ProductsDto products = findProductsService.findProducts(name, price, location, minQuantity, allParams);

        return ResponseEntity.ok(products);
    }
}
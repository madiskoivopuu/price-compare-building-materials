package priceCompare.backend.controller;

import static org.springframework.http.HttpStatus.OK;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import priceCompare.backend.dto.CategoriesDto;
import priceCompare.backend.service.CategoryServiceImpl;

@Slf4j
@RestController
@RequestMapping(value = "/categories")
public class Controller {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping
    @ResponseStatus(OK)

    public ResponseEntity<CategoriesDto> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }
}

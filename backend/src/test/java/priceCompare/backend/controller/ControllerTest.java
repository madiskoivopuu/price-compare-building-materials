package priceCompare.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import priceCompare.backend.dto.CategoriesDto;
import priceCompare.backend.dto.CategoryDto;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.service.CategoryServiceImpl;
import priceCompare.backend.service.FindProductsServiceImpl;

import java.util.List;

@WebMvcTest(Controller.class)
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryServiceImpl categoryService;

    @MockBean
    private FindProductsServiceImpl findProductsService;

    @Test
    void testGetCategories() throws Exception {
        CategoryDto mockCategoryDto = CategoryDto.builder()
                .name("Puit")
                .subcategories(List.of("Prussid", "Lauad", "Höövelpuit"))
                .build();

        CategoriesDto mockCategoriesDto = CategoriesDto.builder()
                .categories(List.of(mockCategoryDto))
                .build();

        when(categoryService.getCategories()).thenReturn(mockCategoriesDto);

        mockMvc.perform(get("/request/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.categories[0].name").value("Puit"))
                .andExpect(jsonPath("$.categories[0].subcategories[0]").value("Prussid"))
                .andExpect(jsonPath("$.categories[0].subcategories[1]").value("Lauad"))
                .andExpect(jsonPath("$.categories[0].subcategories[2]").value("Höövelpuit"));
    }

    @Test
    void testSearchProducts() throws Exception {
        ProductDto mockProduct1 = ProductDto.builder()
                .name("Product A")
                .price(100.0)
                .build();

        ProductDto mockProduct2 = ProductDto.builder()
                .name("Product B")
                .price(200.0)
                .build();

        ProductsDto mockProductsDto = ProductsDto.builder()
                .products(List.of(mockProduct1, mockProduct2))
                .build();

        when(findProductsService.findProducts(any(Category.class), any(Subcategory.class)))
                .thenReturn(mockProductsDto);

        mockMvc.perform(get("/request/search")
                        .param("category", "Ehitusplaadid")
                        .param("subcategory", "Kipsplaat")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.products[0].name").value("Product A"))
                .andExpect(jsonPath("$.products[0].price").value(100.0))
                .andExpect(jsonPath("$.products[1].name").value("Product B"))
                .andExpect(jsonPath("$.products[1].price").value(200.0));
    }
}

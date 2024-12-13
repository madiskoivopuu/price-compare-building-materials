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
import priceCompare.backend.dto.*;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.service.CategoryService;
import priceCompare.backend.service.FilterProductsService;
import priceCompare.backend.service.FindProductsService;
import priceCompare.backend.service.ProxyImageLoadingService;
import priceCompare.backend.service.SearchCachingService;

import java.util.List;

@WebMvcTest(Controller.class)
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private FindProductsService findProductsService;

    @MockBean
    private FilterProductsService filterProductsService;

    @MockBean
    private SearchCachingService searchCachingService;

    @MockBean
    private ProxyImageLoadingService proxyImageLoadingService;

    @Test
    void testGetCategories() throws Exception {
        CategoryDto mockCategoryDto = CategoryDto.builder()
                .name("Puit")
                .subcategories(
                    List.of(
                            SubCategoryDto.builder().displayName("Prussid").build(),
                            SubCategoryDto.builder().displayName("Lauad").build(),
                            SubCategoryDto.builder().displayName("Höövelpuit").build()
                    )
                )
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
                .andExpect(jsonPath("$.categories[0].subcategories[0].displayName").value("Prussid"))
                .andExpect(jsonPath("$.categories[0].subcategories[1].displayName").value("Lauad"))
                .andExpect(jsonPath("$.categories[0].subcategories[2].displayName").value("Höövelpuit"));
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

        when(findProductsService.findProducts(any(String.class), any(Subcategory.class)))
                .thenReturn(mockProductsDto);

        mockMvc.perform(get("/request/search")
                        .param("keyword", "kipsplaat")
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

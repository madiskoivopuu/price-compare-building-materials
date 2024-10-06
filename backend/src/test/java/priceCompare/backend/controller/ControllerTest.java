package priceCompare.backend.controller;

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
}

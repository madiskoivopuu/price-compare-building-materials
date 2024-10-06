package priceCompare.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import priceCompare.backend.dto.CategoriesDto;
import priceCompare.backend.dto.CategoryDto;
import priceCompare.backend.enums.Category;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void testGetCategories() {
        try (MockedStatic<Category> mockedCategory = mockStatic(Category.class)) {
            Category mockCategory = Category.PUIT;

            mockedCategory.when(Category::values).thenReturn(new Category[]{mockCategory});

            CategoriesDto result = categoryService.getCategories();

            assertEquals(1, result.getCategories().size());
            CategoryDto categoryDto = result.getCategories().getFirst();

            assertEquals("Puit", categoryDto.getName());

            List<String> subcategories = categoryDto.getSubcategories();
            assertEquals(3, subcategories.size());
            assertEquals("Prussid", subcategories.get(0));
            assertEquals("Lauad", subcategories.get(1));
            assertEquals("Höövelpuit", subcategories.get(2));
        }
    }
}

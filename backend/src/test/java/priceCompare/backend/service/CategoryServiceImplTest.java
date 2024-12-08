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
import priceCompare.backend.dto.SubCategoryDto;
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

            List<SubCategoryDto> subcategories = categoryDto.getSubcategories();
            assertEquals(11, subcategories.size());
            assertEquals("Prussid", subcategories.get(0).getDisplayName());
            assertEquals("Lauad", subcategories.get(1).getDisplayName());
            assertEquals("Kalibreeritud prussid", subcategories.get(2).getDisplayName());
            assertEquals("Kalibreeritud lauad", subcategories.get(3).getDisplayName());
            assertEquals("Höövelprussid", subcategories.get(4).getDisplayName());
            assertEquals("Höövellauad", subcategories.get(5).getDisplayName());
            assertEquals("Servamata", subcategories.get(6).getDisplayName());
            assertEquals("Liimpuit", subcategories.get(7).getDisplayName());
            assertEquals("Põrandalauad", subcategories.get(8).getDisplayName());
            assertEquals("Terrassilauad", subcategories.get(9).getDisplayName());
            assertEquals("Sisevoodrilauad", subcategories.get(10).getDisplayName());
        }
    }
}

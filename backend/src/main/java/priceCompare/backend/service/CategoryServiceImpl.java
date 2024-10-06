package priceCompare.backend.service;

import org.springframework.stereotype.Service;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.dto.CategoriesDto;
import priceCompare.backend.dto.CategoryDto;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public CategoriesDto getCategories() {
        List<CategoryDto> categoryResources = new ArrayList<>();

        for (Category category : Category.values()) {
            List<String> subcategoryNames = new ArrayList<>();
            for (Subcategory subcategory : category.getSubcategories()) {
                subcategoryNames.add(subcategory.getDisplayName());
            }
            CategoryDto categoryDto = CategoryDto.builder()
                    .name(category.getDisplayName())
                    .subcategories(subcategoryNames)
                    .build();
            categoryResources.add(categoryDto);
        }

        return CategoriesDto.builder()
                .categories(categoryResources)
                .build();
    }
}

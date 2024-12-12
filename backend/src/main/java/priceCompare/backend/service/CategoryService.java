package priceCompare.backend.service;

import org.springframework.stereotype.Service;
import priceCompare.backend.dto.FilterDto;
import priceCompare.backend.dto.SubCategoryDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Filter;
import priceCompare.backend.enums.Keyword;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.dto.CategoriesDto;
import priceCompare.backend.dto.CategoryDto;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    public CategoriesDto getCategories() {
        List<CategoryDto> categoryResources = new ArrayList<>();

        for (Category category : Category.values()) {
            List<SubCategoryDto> subcategories = new ArrayList<>();
            for (Subcategory subcategory : category.getSubcategories()) {
                List<FilterDto> filters = new ArrayList<>();
                for (Filter filter : subcategory.getFilters()){
                    List<String> keywordDisplayNames = new ArrayList<>();
                    for (Keyword keyword : filter.getKeywords()){
                        keywordDisplayNames.add(keyword.getDisplayName());
                    }
                    FilterDto filterDto = FilterDto.builder()
                            .displayName(filter.getDisplayName())
                            .keywords(keywordDisplayNames)
                            .build();
                    filters.add(filterDto);
                }
                SubCategoryDto subCategoryDto = SubCategoryDto.builder()
                        .displayName(subcategory.getDisplayName())
                        .filters(filters)
                        .build();
                subcategories.add(subCategoryDto);
            }
            CategoryDto categoryDto = CategoryDto.builder()
                    .name(category.getDisplayName())
                    .subcategories(subcategories)
                    .build();
            categoryResources.add(categoryDto);
        }

        return CategoriesDto.builder()
                .categories(categoryResources)
                .build();
    }
}

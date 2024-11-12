package priceCompare.backend.utils;

import priceCompare.backend.enums.Subcategory;
import java.util.List;
import java.util.Map;

public class CategoryNameChecker {
    public static boolean checkIsCorrectProductCategory(String categoryName, Subcategory category, Map<Subcategory, List<String>> categoryMap) {
        if (category == null) return true;
        for (String categoryInMap : categoryMap.get(category)){
            if (categoryName.toLowerCase().contains(categoryInMap.toLowerCase())) return true;
        }
        return false;
    }
}

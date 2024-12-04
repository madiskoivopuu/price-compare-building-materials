package priceCompare.backend.utils;

import priceCompare.backend.enums.Subcategory;

import java.util.List;

import static priceCompare.backend.utils.ProductNameChecker.checkProductNameCorrespondsToSearch;

public class CategoryKeywordChecker {
    public static boolean checkContainsRequiredKeyword(String productName, Subcategory category) {
        List<String> keywords = CategoryKeywordCheckMapping.categoryMap.get(category);
        for (String keyword : keywords) {
            if (checkProductNameCorrespondsToSearch(productName, keyword)) return true;
        }
        return false;
    }
}

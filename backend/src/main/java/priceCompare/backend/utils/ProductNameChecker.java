package priceCompare.backend.utils;

public class ProductNameChecker {
    public static boolean checkProductNameCorrespondsToSearch(String productName, String searchKeyword) {
        String normalizedProductName = productName.toLowerCase().replaceAll("\\p{Punct}", "");
        String normalizedSearchKeyword = searchKeyword.toLowerCase().replaceAll("\\p{Punct}", "");

        String[] keywords = normalizedSearchKeyword.split("\\s+");

        for (String keyword : keywords) {
            if (!normalizedProductName.contains(keyword)) {
                return false;
            }
        }
        return true; // All keywords are present, return true
    }
}

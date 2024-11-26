package priceCompare.backend.stores.ehomer.service;

import priceCompare.backend.enums.Subcategory;

import java.net.URI;

public class EhomerApis {
    public static URI getByCategoryUri(String categoryName) {
        return URI.create(
                String.format(
                        "https://eucs4.klevu.com/cloud-search/n-search/search?ticket=klevu-14864759937065183" +
                                "&paginationStartsFrom=0" +
                                "&sortPrice=false" +
                                "&ipAddress=undefined" +
                                "&analyticsApiKey=klevu-14864759937065183" +
                                "&showOutOfStockProducts=true" +
                                "&klevuFetchPopularTerms=false" +
                                "&klevu_priceInterval=500" +
                                "&fetchMinMaxPrice=true" +
                                "&klevu_multiSelectFilters=true" +
                                "&noOfResults=256" +
                                "&klevuSort=rel" +
                                "&enableFilters=true" +
                                "&filterResults=category%%3A%s" +
                                "&visibility=search" +
                                "&category=KLEVU_PRODUCT" +
                                "&klevu_filterLimit=50" +
                                "&sv=2215&lsqt=WILDCARD_AND" +
                                "&responseType=json" +
                                "&klevu_loginCustomerGroup=",
                        categoryName
                )
        );
    }

    public static URI getByCategoryAndKeywordUri(String keyword, String categoryName) {
        return URI.create(
                String.format(
                        "https://eucs4.klevu.com/cloud-search/n-search/search?ticket=klevu-14864759937065183" +
                                "&term=%s" +
                                "&paginationStartsFrom=0" +
                                "&sortPrice=false" +
                                "&ipAddress=undefined" +
                                "&analyticsApiKey=klevu-14864759937065183" +
                                "&showOutOfStockProducts=true" +
                                "&klevuFetchPopularTerms=false" +
                                "&klevu_priceInterval=500" +
                                "&fetchMinMaxPrice=true" +
                                "&klevu_multiSelectFilters=true" +
                                "&noOfResults=256" +
                                "&klevuSort=rel" +
                                "&enableFilters=true" +
                                "&filterResults=category%%3A%s" +
                                "&visibility=search" +
                                "&category=KLEVU_PRODUCT" +
                                "&klevu_filterLimit=50" +
                                "&sv=2215&lsqt=WILDCARD_AND" +
                                "&responseType=json" +
                                "&klevu_loginCustomerGroup=",
                        replaceSpaces(keyword), categoryName
                )

        );
    }

    public static URI getByKeywordUri(String keyword) {
        return URI.create(
                String.format(
                        "https://eucs4.klevu.com/cloud-search/n-search/search?ticket=klevu-14864759937065183" +
                                "&term=%s" +
                                "&paginationStartsFrom=0" +
                                "&sortPrice=false" +
                                "&ipAddress=undefined" +
                                "&analyticsApiKey=klevu-14864759937065183" +
                                "&showOutOfStockProducts=true" +
                                "&klevuFetchPopularTerms=false" +
                                "&klevu_priceInterval=500" +
                                "&fetchMinMaxPrice=true" +
                                "&klevu_multiSelectFilters=true" +
                                "&noOfResults=256" +
                                "&klevuSort=rel" +
                                "&enableFilters=true" +
                                "&visibility=search" +
                                "&category=KLEVU_PRODUCT" +
                                "&klevu_filterLimit=50" +
                                "&sv=2215&lsqt=WILDCARD_AND" +
                                "&responseType=json" +
                                "&klevu_loginCustomerGroup=",
                        replaceSpaces(keyword)
                )
        );
    }
    private static String replaceSpaces(String keyword){
        return keyword.replaceAll(" ", "%20");
    }
}

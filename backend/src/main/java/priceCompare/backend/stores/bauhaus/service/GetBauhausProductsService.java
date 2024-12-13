package priceCompare.backend.stores.bauhaus.service;

import static priceCompare.backend.enums.Unit.TK;
import static priceCompare.backend.stores.bauhaus.service.BauhausApis.*;
import static priceCompare.backend.stores.bauhaus.service.EmaterjalToBauhausCategoryMapping.categoryMap;
import static priceCompare.backend.stores.bauhaus.service.EmaterjalToBauhausCategoryMapping.categoryRootMap;
import static priceCompare.backend.utils.CategoryKeywordChecker.checkContainsRequiredKeyword;
import static priceCompare.backend.utils.ProductNameChecker.checkProductNameCorrespondsToSearch;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import priceCompare.backend.httpclient.HttpClientService;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Store;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.enums.Unit;
import priceCompare.backend.stores.GetStoreProductsService;
import priceCompare.backend.stores.dto.ProductParseDto;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class GetBauhausProductsService implements GetStoreProductsService {
    private static final String BAUHAUS_API_URL = "https://eucs32v2.ksearchnet.com/cs/v2/search";
    private static final String API_KEY = "klevu-169590957278116052";
    private static final int SEARCH_API_PAGE_SIZE = 256;

    private final HttpClientService httpClientService;
    private final BauhausStockFetcher locationStockInformationFetcher;

    @Override
    public ProductsDto searchForProducts(String keyword, Subcategory subcategory) {
        List<ProductParseDto> products = new ArrayList<>();

        if (subcategory != null) {
            if (keyword == null || keyword.isEmpty()) {
                products.addAll(getProductsByCategory(subcategory));
            } else {
                products.addAll(getProductsByKeywordAndCategory(keyword, subcategory));
            }
        } else if (keyword != null) {
            products.addAll(getProductsByKeyword(keyword));
        }

        products = locationStockInformationFetcher.fetchLocationInfo(products);

        return ProductsDto.builder()
                .products(products.stream().map(ProductParseDto::getProduct).toList())
                .build();
    }

    public List<ProductParseDto> getProductsByCategory(Subcategory subcategory) {
        List<ProductParseDto> products = new ArrayList<>();
        List<String> categoryPaths = getCategoryCorrespondingCategoryPaths(subcategory);
        for (String categoryPath : categoryPaths) {
            JSONObject response = httpClientService.PostWithBodyAndReturnJson(URI.create(BAUHAUS_API_URL),
                    buildRequestBodyWithCategory(categoryPath, API_KEY, SEARCH_API_PAGE_SIZE));
            products.addAll(parseResponse(response, null, subcategory));
        }
        return products;
    }

    public List<ProductParseDto> getProductsByKeyword(String keyword) {
        JSONObject response = httpClientService.PostWithBodyAndReturnJson(URI.create(BAUHAUS_API_URL),
                buildRequestBodyWithKeyword(keyword, API_KEY, SEARCH_API_PAGE_SIZE));
        return parseResponse(response, keyword, null);
    }

    public List<ProductParseDto> getProductsByKeywordAndCategory(String keyword, Subcategory subcategory) {
        List<String> categories = getBauhausCategories(subcategory);
        if (categories.isEmpty()) return new ArrayList<>();
        JSONObject response = httpClientService.PostWithBodyAndReturnJson(URI.create(BAUHAUS_API_URL),
                buildRequestBodyWithKeywordAndCategory(keyword, categories , API_KEY, SEARCH_API_PAGE_SIZE));
        return parseResponse(response, keyword, subcategory);
    }

    private List<ProductParseDto> parseResponse(JSONObject response, String keyword, Subcategory subcategory) {
        JSONArray productsArr = response.getJSONArray("queryResults").getJSONObject(0).getJSONArray("records");

        List<ProductParseDto> productList = new ArrayList<>();
        for(int i = 0; i < productsArr.length(); i++) {
            JSONObject productNode = productsArr.getJSONObject(i);

            String productName = productNode.getString("name");
            if (keyword!=null && !keyword.isEmpty() && !checkProductNameCorrespondsToSearch(productName, keyword)) continue;

            if ((keyword == null || keyword.isEmpty()) && !checkContainsRequiredKeyword(productName, subcategory)) continue;

            try {
                String ifactorUnit = productNode.optString("ifactor_unit", null);
                Unit unit = (ifactorUnit != null) ? Unit.fromDisplayName(ifactorUnit) : TK;

                ProductDto product = ProductDto.builder()
                        .store(Store.BAUHAUS)
                        .name(productName)
                        .price(productNode.getDouble("price"))
                        .unit(unit)
                        .linkToProduct(productNode.getString("url"))
                        .linkToPicture(productNode.getString("imageUrl").replace("needtochange/",""))
                        .build();
                ProductParseDto productIntermediateInfo = ProductParseDto.builder()
                        .product(product)
                        .sku(productNode.getString("sku"))
                        .build();
                productList.add(productIntermediateInfo);
            } catch(IllegalArgumentException e) {
                log.printf(Level.WARN, "Bauhaus products service: %s%n", e.getMessage());
            }
        }
        return productList;
    }

    private List<String> getBauhausCategories(Subcategory subcategory) {
        return categoryMap.get(subcategory);
    }

    private List<String> getCategoryCorrespondingCategoryPaths(Subcategory subcategory) {
        List<String> categoryPaths = new ArrayList<>();
        String categoryPathRoot = categoryRootMap.get(subcategory);
        List<String> categoryNames = categoryMap.get(subcategory);
        for (String categoryName : categoryNames) {
            categoryPaths.add(String.format("%s%s", categoryPathRoot, categoryName));
        }
        return categoryPaths;
    }
}

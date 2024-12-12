package priceCompare.backend.stores.ehituseabc.service;

import static priceCompare.backend.stores.ehituseabc.service.EhituseAbcApis.*;
import static priceCompare.backend.stores.ehituseabc.service.EmaterjalToEhituseAbcCategoryMapping.categoryMap;
import static priceCompare.backend.stores.ehituseabc.service.EmaterjalToEhituseAbcCategoryMapping.categoryRootMap;
import static priceCompare.backend.utils.CategoryKeywordChecker.checkContainsRequiredKeyword;
import static priceCompare.backend.utils.ProductNameChecker.checkProductNameCorrespondsToSearch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class GetEhituseAbcProductsService implements GetStoreProductsService {
    private static final String EHITUSEABC_API_URL = "https://eucs32v2.ksearchnet.com/cs/v2/search";
    private static final String API_KEY = "klevu-168180264665813326";
    private static final int SEARCH_API_PAGE_SIZE = 128;

    private final HttpClientService httpClientService;
    private final EhituseAbcStockFetcher locationStockInformationFetcher;

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
            JSONObject response = httpClientService.PostWithBodyAndReturnJson(URI.create(EHITUSEABC_API_URL),
                    buildRequestBodyWithCategory(categoryPath, API_KEY, SEARCH_API_PAGE_SIZE));
            products.addAll(parseResponse(response, null, subcategory));
        }
        return products;
    }

    public List<ProductParseDto> getProductsByKeyword(String keyword) {
        JSONObject response = httpClientService.PostWithBodyAndReturnJson(URI.create(EHITUSEABC_API_URL),
                buildRequestBodyWithKeyword(keyword, API_KEY, SEARCH_API_PAGE_SIZE));
        return parseResponse(response, keyword, null);
    }

    public List<ProductParseDto> getProductsByKeywordAndCategory(String keyword, Subcategory subcategory) {
        List<String> categories = getEhituseAbcCategories(subcategory);
        if (categories.isEmpty()) return new ArrayList<>();
        JSONObject response = httpClientService.PostWithBodyAndReturnJson(URI.create(EHITUSEABC_API_URL),
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
                Unit unit = extractUnit(productNode.getString("additionalDataToReturn"));
                ProductDto product = ProductDto.builder()
                        .store(Store.EHITUSEABC)
                        .name(productName)
                        .price(productNode.getDouble("salePrice"))
                        .unit(unit)
                        .linkToProduct(productNode.getString("url"))
                        .linkToPicture(productNode.getString("imageUrl"))
                        .build();
                ProductParseDto productIntermediateInfo = ProductParseDto.builder()
                        .product(product)
                        .searchApiProductInfo(productNode.toString())
                        .build();
                productList.add(productIntermediateInfo);
            } catch(IllegalArgumentException e) {
                log.printf(Level.WARN, "EhituseAbc products service: %s%n", e.getMessage());
            }
        }
        return productList;
    }

    public Unit extractUnit(String additionalDataToReturn) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode additionalData = mapper.readTree(additionalDataToReturn);
            String unitDisplayName = additionalData.path("priceUnit").asText();
            return Unit.fromDisplayName(unitDisplayName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse unit from additionalDataToReturn: " + additionalDataToReturn, e);
        }
    }

    private List<String> getEhituseAbcCategories(Subcategory subcategory) {
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

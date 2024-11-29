package priceCompare.backend.stores.bauhof.service;

import static priceCompare.backend.utils.CategoryKeywordMapping.categoryKeywordMap;
import static priceCompare.backend.utils.CategoryNameChecker.checkIsCorrectProductCategory;
import static priceCompare.backend.utils.ProductNameChecker.checkProductNameCorrespondsToSearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import priceCompare.backend.HttpClient.HttpClientService;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Store;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.enums.Unit;
import priceCompare.backend.stores.GetStoreProductsService;
import priceCompare.backend.stores.dto.ProductParseDto;

import java.util.ArrayList;
import java.util.List;


@Service
public class GetBauhofProductsServiceImpl implements GetStoreProductsService {

    private static final String BAUHOF_PRODUCT_URL_BEGINNING = "https://www.bauhof.ee/et/p/";
    private static final int SEARCH_API_PAGE_SIZE = 64;
    private static final int FETCH_MAX_NUM_PRODUCTS = 128;

    private final HttpClientService httpClientService;
    private final LocationStockInformationFetcherBauhof locationStockInformationFetcher;
    final BauhofApis apis;

    public GetBauhofProductsServiceImpl(HttpClientService httpClientService, LocationStockInformationFetcherBauhof locationStockInformationFetcher, BauhofApis apis) {
        this.httpClientService = httpClientService;
        this.locationStockInformationFetcher = locationStockInformationFetcher;
        this.apis = apis;
    }

    @Override
    public ProductsDto searchForProducts(String keyword, Subcategory subcategory) {

        //search without keyword
        if (keyword == null || keyword.isEmpty()) {
            keyword = categoryKeywordMap.get(subcategory);
        }

        List<ProductParseDto> products = new ArrayList<>();
        int offset = 0;
        int numProductsTotal = 0;

        do {
            System.out.println(offset);
            JSONObject response = httpClientService.PostWithBodyAndReturnJson(apis.buildUrl(keyword), apis.buildRequestBody(keyword, offset, SEARCH_API_PAGE_SIZE));
            if(response == null && numProductsTotal == 0) {
                System.err.println("Bauhof products service: very first search API request failed, cannot continue fetching products");
                break;
            }
            if(response == null) { // we should still continue with the rest of the request even if part of products are missing
                offset += SEARCH_API_PAGE_SIZE;
                continue;
            }

            numProductsTotal = response.getJSONArray("queryResults").getJSONObject(0).getJSONObject("meta").getInt("totalResultsFound");
            numProductsTotal = Math.min(numProductsTotal, FETCH_MAX_NUM_PRODUCTS); // to avoid very time-consuming product fetches, we set the max number to some arbitrary value
            offset += SEARCH_API_PAGE_SIZE;

            products.addAll(parseResponse(response.toString(), keyword, subcategory));

            products = locationStockInformationFetcher.fetchLocationInfo(products);

        } while(offset < numProductsTotal);

        return ProductsDto.builder()
                .products(products.stream().map(ProductParseDto::getProduct).toList())
                .build();
    }

    private List<ProductParseDto> parseResponse(String response, String keyword, Subcategory subcategory) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode recordsNode = rootNode.path("queryResults").get(0).path("records");

        List<ProductParseDto> productList = new ArrayList<>();
        for (JsonNode productNode : recordsNode) {

            String productName = productNode.path("name").asText();
            if (!checkProductNameCorrespondsToSearch(productName, keyword)) continue;

            String categoryName = productNode.path("klevu_category").asText();
            if (!checkIsCorrectProductCategory(categoryName, subcategory, EmaterjalToBauhofCategoryMapping.categoryMap)) continue;

            if(productNode.path("inStock").asText("yes").equals("no")) continue;

            try {
                ProductDto product = ProductDto.builder()
                        .store(Store.BAUHOF)
                        .name(productName)
                        .price(productNode.path("price").asDouble())
                        .unit(Unit.fromDisplayName(productNode.path("unit_id").asText()))
                        .linkToProduct(BAUHOF_PRODUCT_URL_BEGINNING + productNode.path("sku").asText() + "/" + productNode.path("url_key").asText())
                        .linkToPicture(productNode.path("imageUrl").asText())
                        .build();
                ProductParseDto productIntermediateInfo = ProductParseDto.builder()
                        .product(product)
                        .sku(productNode.path("sku").asText())
                        .build();
                productList.add(productIntermediateInfo);
            } catch(IllegalArgumentException e) {
                System.err.println("Bauhof products service: " + e.getMessage());
                System.err.println(productNode.path("url_key").asText());
            }
        }

        return productList;
    }
}

package priceCompare.backend.stores.bauhof.service;

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
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Service
public class GetBauhofProductsServiceImpl implements GetStoreProductsService {

    private static final String BAUHOF_API_URL = "https://www.bauhof.ee/api/klevu/search";
    private static final String API_KEY = "---protected---";
    private static final String BAUHOF_PRODUCT_URL_BEGINNING = "https://www.bauhof.ee/et/p/";
    private static final int SEARCH_API_PAGE_SIZE = 64;
    private static final int FETCH_MAX_NUM_PRODUCTS = 128;

    private final HttpClientService httpClientService;
    private final LocationStockInformationFetcherBauhof locationStockInformationFetcher;

    public GetBauhofProductsServiceImpl(HttpClientService httpClientService, LocationStockInformationFetcherBauhof locationStockInformationFetcher) {
        this.httpClientService = httpClientService;
        this.locationStockInformationFetcher = locationStockInformationFetcher;
    }

    @Override
    public ProductsDto searchForProducts(String query, Subcategory subcategory) {

        if (query == null || query.isEmpty()) {
            return null;//quick fix until we find better solution for bauhof search without keyword
        }

        List<ProductDto> products = new ArrayList<>();
        int offset = 0;
        int numProductsTotal = 0;

        do {
            JSONObject response = httpClientService.PostWithBody(buildUrl(query), buildRequestBody(query, offset));
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

            products.addAll(parseResponse(response.toString(), query, subcategory));
        } while(offset < numProductsTotal);

        return ProductsDto.builder()
                .products(products)
                .build();
    }

    private URI buildUrl(String keyword){
        return URI.create(String.format("%s?query=%s", BAUHOF_API_URL, URLEncoder.encode(keyword, StandardCharsets.UTF_8)));
    }

    private String buildRequestBody(String keyword, int offset) {
        return String.format(
                "{\"context\":{\"apiKeys\":[\"%s\"]},\"recordQueries\":[{\"id\":\"search\",\"typeOfRequest\":\"SEARCH\",\"settings\":{\"query\":{\"term\":\"%s\"},\"id\":\"search\",\"limit\":%d,\"typeOfRecords\":[\"KLEVU_PRODUCT\"],\"offset\":%d,\"sort\":\"RELEVANCE\"}}]}",
                API_KEY, keyword, SEARCH_API_PAGE_SIZE, offset);
    }

    private List<ProductDto> parseResponse(String response, String keyword, Subcategory subcategory) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode recordsNode = rootNode.path("queryResults").get(0).path("records");

        List<ProductDto> productList = new ArrayList<>();
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
                        .stock(locationStockInformationFetcher.getLocationAndStockInformation(productNode.path("sku").asText()))
                        .build();
                productList.add(product);
            } catch(IllegalArgumentException e) {
                System.err.println("Bauhof products service: " + e.getMessage());
                System.err.println(productNode.path("url_key").asText());
            }
        }

        return productList;
    }
}

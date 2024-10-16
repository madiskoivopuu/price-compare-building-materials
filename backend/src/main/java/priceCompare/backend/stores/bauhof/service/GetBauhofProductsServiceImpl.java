package priceCompare.backend.stores.bauhof.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priceCompare.backend.HttpClient.HttpClientService;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Store;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.enums.Unit;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetBauhofProductsServiceImpl implements GetBauhofProductsService {

    private static final String BAUHOF_API_URL = "https://www.bauhof.ee/api/klevu/search";
    private static final String API_KEY = "---protected---";
    private static final String BAUHOF_PRODUCT_URL_BEGINNING = "https://www.bauhof.ee/et/p/";

    @Autowired
    private HttpClientService httpClientService;

    @Override
    public ProductsDto getBauhofProducts(String keyword, Category category, Subcategory subcategory) {
        JSONObject response = httpClientService.PostWithBody(buildUrl(keyword), buildRequestBody(keyword));
        List<ProductDto> productList = parseResponse(response.toString());
        return ProductsDto.builder()
                .products(productList)
                .build();
    }

    private URI buildUrl(String keyword){
        return URI.create(String.format("%s?query=%s", BAUHOF_API_URL, keyword));
    }

    private String buildRequestBody(String keyword) {
        return String.format(
                "{\"context\":{\"apiKeys\":[\"%s\"]},\"recordQueries\":[{\"id\":\"search\",\"typeOfRequest\":\"SEARCH\",\"settings\":{\"query\":{\"term\":\"%s\"},\"id\":\"search\",\"limit\":32,\"typeOfRecords\":[\"KLEVU_PRODUCT\"],\"offset\":0,\"sort\":\"RELEVANCE\"}}]}",
                API_KEY, keyword);
    }

    private List<ProductDto> parseResponse(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode recordsNode = rootNode.path("queryResults").get(0).path("records");

        List<ProductDto> productList = new ArrayList<>();
        for (JsonNode productNode : recordsNode) {
            ProductDto product = ProductDto.builder()
                    .store(Store.BAUHOF)
                    .name(productNode.path("name").asText())
                    .price(productNode.path("price").asDouble())
                    .unit(Unit.fromDisplayName(productNode.path("unit_id").asText()))
                    .linkToProduct(BAUHOF_PRODUCT_URL_BEGINNING + productNode.path("sku").asText() + "/" + productNode.path("url_key").asText())
                    .linkToPicture(productNode.path("imageUrl").asText())
                    .build();
            productList.add(product);
        }

        return productList;
    }
}

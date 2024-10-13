package priceCompare.backend.stores.bauhof.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.enums.Unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetBauhofProductsServiceImpl implements GetBauhofProductsService {

    private static final String BAUHOF_API_URL = "https://www.bauhof.ee/api/klevu/search";
    private static final String API_KEY = "---protected---";
    private static final String BAUHOF_PRODUCT_URL_BEGINNING = "https://www.bauhof.ee/et/p/";

    @Override
    public ProductsDto getBauhofProducts(String keyword, Category category, Subcategory subcategory) {
        List<ProductDto> productList = new ArrayList<>();

        try {
            HttpURLConnection connection = createConnection(keyword);
            String requestBody = buildRequestBody(keyword);
            sendRequest(connection, requestBody);

            String response = readResponse(connection);
            productList = parseResponse(response);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ProductsDto.builder()
                .products(productList)
                .build();
    }

    private HttpURLConnection createConnection(String keyword) throws IOException {
        String urlString = BAUHOF_API_URL + "?query=" + keyword;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setDoOutput(true);
        return connection;
    }

    private String buildRequestBody(String keyword) {
        return String.format(
                "{\"context\":{\"apiKeys\":[\"%s\"]},\"recordQueries\":[{\"id\":\"search\",\"typeOfRequest\":\"SEARCH\",\"settings\":{\"query\":{\"term\":\"%s\"},\"id\":\"search\",\"limit\":32,\"typeOfRecords\":[\"KLEVU_PRODUCT\"],\"offset\":0,\"sort\":\"RELEVANCE\"}}]}",
                API_KEY, keyword);
    }

    private void sendRequest(HttpURLConnection connection, String requestBody) throws IOException {
        connection.getOutputStream().write(requestBody.getBytes());
    }

    private String readResponse(HttpURLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }
        reader.close();
        return responseBuilder.toString();
    }

    private List<ProductDto> parseResponse(String response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode recordsNode = rootNode.path("queryResults").get(0).path("records");

        List<ProductDto> productList = new ArrayList<>();
        for (JsonNode productNode : recordsNode) {
            ProductDto product = ProductDto.builder()
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

package priceCompare.backend.stores.krauta.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Store;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.enums.Unit;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetKRautaProductsServiceImpl implements GetKRautaProductsService {
    public static final String QUERY_KEY_FETCH_PRODUCTS = "NSykN7XLeh4CPAauPZ4TAvVuFUjPt7QY";

    private URI formatSearchUrl(String query, Subcategory subcategory) {
        // TODO: map ematerjal.ee categories to krauta
        String queryString = "query_key=" + QUERY_KEY_FETCH_PRODUCTS + "&search_query=" + URLEncoder.encode(query, StandardCharsets.UTF_8);
        return URI.create("https://sd.searchnode.net/v1/query/docs?" + queryString);
    }

    /**
     * Fetches a list of products from the search API.
     * @param query
     * @param category
     * @param subcategory
     * @return A list of ProductDtos with their URLs (and some other metadata that was able to be fetched from the search api)
     */
    private ProductsDto fetchProductsListFromKRauta(String query, Category category, Subcategory subcategory) {
        try(HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build()) {
            HttpRequest productsReq = HttpRequest.newBuilder()
                    .uri(formatSearchUrl(query, subcategory))
                    .GET()
                    .build();

            HttpResponse<String> response;
            try {
                response = client.send(productsReq, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                // TODO: log this issue too
                return ProductsDto.builder().build();
            }

            if(response.statusCode() != 200) {
                // TODO: log problem
                return ProductsDto.builder().build();
            }

            JSONObject productsJson = new JSONObject(response.body());
            List<ProductDto> products = new ArrayList<>();

            JSONArray docs = productsJson.getJSONArray("docs");
            for(int i = 0; i < docs.length(); i++) {
                JSONObject singleProductJson = docs.getJSONObject(i);

                if(!singleProductJson.getBoolean("inStock"))
                    continue;

                try {
                    ProductDto productDto = ProductDto.builder()
                            .store(Store.KRAUTA)
                            .linkToProduct("https://k-rauta.ee" + singleProductJson.getString("url"))
                            .linkToPicture(singleProductJson.getString("image"))
                            .unit(Unit.fromDisplayName(singleProductJson.getString("measurementUnit")))
                            .name(singleProductJson.getString("title"))
                            .price(singleProductJson.getDouble("priceDefault"))
                            .build();

                    products.add(productDto);
                } catch(IllegalArgumentException e) {
                    System.err.println("Error getting certain values from JSON for product: " + e.getMessage());
                    System.err.println(singleProductJson.toString());
                }
            }

            return ProductsDto.builder()
                    .products(products)
                    .build();
        }
    }

    /**
     * Fetches all the core information fields for ProductDto-s.
     * @param products A list of products with their URLs stored in the data transfer object
     * @return A new ProductsDto, which has all core fields filled for the products.
     */
    private ProductsDto fetchCoreInfo(ProductsDto products) {
        return null;
    }

    /**
     * Fetches subcategory specific fields into a hashmap inside ProductDto.
     * @param products A list of products with their URLs stored in the data transfer object
     * @return A new ProductsDto, which has all subcategory specific fields filled for the products.
     */
    private ProductsDto fetchSubcategorySpecificInfo(ProductsDto products, Subcategory subcategory) {
        return null;
    }

    /**
     * Fetches all the products & their metadata from K-rauta that match the query and subcategory
     * @param query User input containing keywords for the product
     * @param subcategory Subcategory chosen on Ematerjal.ee
     * @return If subcategory is defined, returns ProductsDto with every object in it having category specific metadata. Otherwise, returns ProductsDto without category metadata.
     */
    @Override
    public ProductsDto getKRautaProducts(String query, Category category, Subcategory subcategory) {
        if(subcategory == null && (query == null || query.isEmpty())) {
            return ProductsDto.builder().build();
        }

        ProductsDto products = fetchProductsListFromKRauta(query, category, subcategory);

        // TODO: fetching core info & subcategory specific info
        /*products = fetchCoreInfoForProducts(products); // fetches location info if available

         if(subcategory != null) {
            // map subcategory to a k-rauta one
            products = fetchSubcategorySpecificInfo(products, subcategory);
         }

         */
        return products;
    }
}
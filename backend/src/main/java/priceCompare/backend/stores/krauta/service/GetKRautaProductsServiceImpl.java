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
    public static final int FETCH_MAX_NUM_PRODUCTS = 1024;
    private final KRautaAPIs apis;

    public GetKRautaProductsServiceImpl(KRautaAPIs apis) {
        this.apis = apis;
    }

    /**
     * Fetches a list of products from the search API.
     * @param query Keywords used in the search
     * @param category Ematerjal.ee main category
     * @param subcategory Ematerjal.ee subcategory
     * @return A list of ProductDtos with their URLs (and some other metadata that was able to be fetched from the search api)
     */
    private ProductsDto fetchProductsListFromKRauta(String query, Category category, Subcategory subcategory) {
        try(HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build()) {

            int offset = 0;
            int numProducts = 0;
            List<ProductDto> products = new ArrayList<>();

            do {
                JSONObject productsJson = apis.fetchPageFromSearchAPI(client, query, subcategory, offset);
                if(productsJson == null && numProducts == 0) {
                    System.err.println("K-rauta products service: very first search API request failed, cannot continue fetching products");
                    break;
                }
                if(productsJson == null) { // we should still continue with the rest of the request even if part of products are missing
                    offset += KRautaAPIs.SEARCH_API_PAGE_SIZE;
                    continue;
                }

                JSONArray docs = productsJson.getJSONArray("docs");
                numProducts = Math.min(productsJson.getInt("doc_count"), FETCH_MAX_NUM_PRODUCTS); // to avoid very time-consuming product fetches, we set the max number to some arbitrary value
                offset += KRautaAPIs.SEARCH_API_PAGE_SIZE;

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
                        System.err.println("K-rauta products service: Error getting certain values from JSON for product: " + e.getMessage());
                        System.err.println(singleProductJson);
                    }
                }
            } while(offset < numProducts);

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
     * Fetches all the in-stock products & their metadata from K-rauta that match the query and subcategory
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
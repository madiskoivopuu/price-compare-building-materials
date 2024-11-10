package priceCompare.backend.stores.espak.service;

import static priceCompare.backend.utils.ProductNameChecker.checkProductNameCorrespondsToSearch;

import com.google.common.collect.Lists;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import priceCompare.backend.HttpClient.HttpClientService;
import priceCompare.backend.dto.*;
import priceCompare.backend.dto.StockInLocationsDto;
import priceCompare.backend.enums.*;
import priceCompare.backend.stores.GetStoreProductsService;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class GetEspakProductsServiceImpl implements GetStoreProductsService {
    public static final int FETCH_MAX_NUM_PRODUCTS = 1024;
    private static final int PRODUCT_BATCH_SIZE = 20;
    private final EspakAPIs apis;

    public GetEspakProductsServiceImpl(EspakAPIs apis) {
        this.apis = apis;
    }

    /**
     * Returns all the products (up to FETCH_MAX_NUM_PRODUCTS) that the espak.ee search API gives us.
     * @param query Query to search products with
     * @param subcategory Ematerjal.ee subcategory
     * @return A list of products that are in stock somewhere. Some core ProductDto fields might be filled
     */
    private List<ProductDto> performEspakSearch(String query, Subcategory subcategory) {
        int page = 0;
        int maxPages = 0;
        List<ProductDto> products = new ArrayList<>();

        do {
            JSONObject searchResponse = apis.fetchPageFromSearchAPI(query, subcategory, page);
            page++;
            if(maxPages == 0 && searchResponse == null) {
                System.err.println("ESPAK products service: very first search API request failed, cannot continue fetching products");
                break;
            }

            if(searchResponse == null) {
                continue;
            }

            maxPages = searchResponse.getJSONArray("results").getJSONObject(0).getInt("nbPages");
            JSONArray productsJsonArr = searchResponse.getJSONArray("results").getJSONObject(0).getJSONArray("hits");

            for(int i = 0; i < productsJsonArr.length(); i++) {
                JSONObject productJson = productsJsonArr.getJSONObject(i);

                String productName = productJson.getString("post_title");
                if (!checkProductNameCorrespondsToSearch(productName, query)) continue;

                ProductDto product = ProductDto.builder()
                        .store(Store.ESPAK)
                        .name(productName)
                        .price(Double.parseDouble(productJson.getString("regular_price")))
                        .linkToProduct(productJson.getString("permalink"))
                        .linkToPicture(productJson.getJSONObject("images").getJSONObject("thumbnail").getString("url"))
                        .stock(mapLocationWithStock())
                        .build();

                products.add(product);
            }
        } while(products.size() < FETCH_MAX_NUM_PRODUCTS && page < maxPages);

        return products;
    }

    /**
     * Fetches core information fields that were not filled in ProductDto only with the search API request
     * @param products All the products that were fetched from the search API
     * @return ProductsDto with core fields filled
     */
    private List<ProductDto> fetchCoreInfoFields(List<ProductDto> products) {
        List<ProductDto> newProducts = new ArrayList<>();
        List<List<ProductDto>> batches = Lists.partition(products, PRODUCT_BATCH_SIZE);

        // send reqs
        for(List<ProductDto> batch : batches) {
            List<CompletableFuture<Document>> responseFutures = new ArrayList<>();
            for(ProductDto product : batch) {
                responseFutures.add(
                        apis.fetchProductPage(product.getLinkToProduct())
                );
            }

            for(int i = 0; i < batch.size(); i++) {
                CompletableFuture<Document> future = responseFutures.get(i);
                ProductDto product = batch.get(i);

                try {
                    Document body = future.join();
                    product = product.toBuilder()
                            .unit(Unit.fromDisplayName(body.select("div.unit").text()))
                            .build();
                } catch(CompletionException e) {
                    System.err.printf("ESPAK products service: Error fetching data from URL: %s, Exception: %s\n", product.getLinkToProduct(), e.getMessage());
                } catch(IllegalArgumentException e) {
                    System.err.println("ESPAK products service: " + e.getMessage());
                    System.err.println(product.getLinkToProduct());
                } finally {
                    newProducts.add(product);
                }
            }
        }

        return newProducts;
    }

    /**
     * Gives all store locations stock information. If stock is not available in a certain store (LocationDto), then the stock will remain at 0. Special cases, where the product is always in stock but the amount isnt mentioned, is shown with stock set to -1
     * @return List of store locations along with the stock in every one. This is hardcoded since Espak always lists products in stock at their Tallinn shop.
     */
    private StockInLocationsDto mapLocationWithStock() {
        List<StockDto> locations = new ArrayList<>();
        for(EspakStoreLocation storeLocation : EspakStoreLocation.values()) {
            if(storeLocation.location.getLocationName() == LocationName.TALLINN) {
                locations.add(
                        StockDto.builder()
                                .location(storeLocation.location)
                                .quantityText("Laos")
                                .additionalNotes("ESPAKi veebipood näitab toodete kättesaadavust ainult Tallinna poes.")
                                .build()
                );
            } else {
                locations.add(
                        StockDto.builder()
                                .location(storeLocation.location)
                                .quantityText("Teadmata")
                                .build()
                );
            }
        }

        return StockInLocationsDto.builder()
                .locations(locations)
                .build();
    }

    @Override
    public ProductsDto searchForProducts(String query, Category category, Subcategory subcategory) {
        List<ProductDto> products = performEspakSearch(query, subcategory);
        products = fetchCoreInfoFields(products);

        return ProductsDto.builder()
                .products(products)
                .build();
    }
}

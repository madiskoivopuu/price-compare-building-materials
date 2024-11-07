package priceCompare.backend.stores.espak.service;

import static priceCompare.backend.utils.ProductNameChecker.checkProductNameCorrespondsToSearch;

import com.google.common.collect.Lists;
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
    private static final int SEARCH_BATCH_SIZE = 8;
    private final HttpClientService httpClientService;

    public GetEspakProductsServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    private URI formatSearchUri(String query, int page) {
        return URI.create(String.format("https://espak.ee/epood/page/%d/?s=%s&post_type=product", page, URLEncoder.encode(query, StandardCharsets.UTF_8)));
    }

    /**
     * Returns all the products (up to FETCH_MAX_NUM_PRODUCTS) that the espak.ee search API gives us.
     * @param query Query to search products with
     * @return A list of products that are in stock somewhere. Some core ProductDto fields might be filled
     */
    private List<ProductDto> performEspakSearch(String query) {
        int page = 1;
        List<ProductDto> products = new ArrayList<>();
        boolean moreProductsToFetch = true;

        do {
            List<CompletableFuture<HttpResponse<String>>> searchPageResponses = new ArrayList<>();
            for(int i = 0; i < SEARCH_BATCH_SIZE; i++)
                searchPageResponses.add(
                        httpClientService.GetStringAsync(formatSearchUri(query, page+i))
                );

            for(CompletableFuture<HttpResponse<String>> responseFuture : searchPageResponses) {
                page++;
                HttpResponse<String> response;
                String htmlPage;

                try {
                    response = responseFuture.join();
                } catch(CompletionException e) {
                    System.err.printf("ESPAK products service: Error fetching data from search page: %s, Exception: %s\n", page, e.getMessage());
                    continue;
                }

                if(response.statusCode() == 404) { // no more products to fetch
                    moreProductsToFetch = false;
                    break;
                }

                Document doc = Jsoup.parse(response.body());
                Elements productElements = doc.select("div.product.type-product");
                if(productElements.isEmpty()) continue; // some pages are randomly empty on espak.ee

                for(Element productElement : productElements) {
                    if(!productElement.select("p.listing-stock-in").text().equals("Laos")) continue;

                    String productName = productElement.select("p.name.product-title > a").text();
                    if (!checkProductNameCorrespondsToSearch(productName, query)) continue;

                    ProductDto product = ProductDto.builder()
                            .store(Store.ESPAK)
                            .name(productName)
                            .price(Double.parseDouble(productElement.select("div.price-wrapper > div:nth-child(1)").text()))
                            .linkToProduct(productElement.select("p.name.product-title > a").attr("href"))
                            .linkToPicture(productElement.select("img").attr("src"))
                            .stock(mapLocationWithStock())
                            .build();

                    products.add(product);
                }
            }
        } while(products.size() < FETCH_MAX_NUM_PRODUCTS && moreProductsToFetch);

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
            List<CompletableFuture<String>> responseFutures = new ArrayList<>();
            for(ProductDto product : batch) {
                responseFutures.add(
                        httpClientService.GetStringAsync(URI.create(product.getLinkToProduct())).thenApply(HttpResponse::body)
                );
            }

            for(int i = 0; i < batch.size(); i++) {
                CompletableFuture<String> future = responseFutures.get(i);
                ProductDto product = batch.get(i);

                try {
                    String resp = future.join();

                    Document body = Jsoup.parse(resp);
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
        List<ProductDto> products = performEspakSearch(query);
        products = fetchCoreInfoFields(products);

        return ProductsDto.builder()
                .products(products)
                .build();
    }
}

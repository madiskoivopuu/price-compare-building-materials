package priceCompare.backend.stores.decora.service;

import static priceCompare.backend.utils.CategoryKeywordChecker.checkContainsRequiredKeyword;
import static priceCompare.backend.utils.ProductNameChecker.checkProductNameCorrespondsToSearch;

import com.google.common.collect.Lists;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import priceCompare.backend.dto.*;
import priceCompare.backend.enums.Store;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.enums.Unit;
import priceCompare.backend.stores.GetStoreProductsService;
import priceCompare.backend.stores.dto.ProductParseDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class GetDecoraProductsServiceImpl implements GetStoreProductsService {

    private final int FETCH_MAX_NUM_PRODUCTS = 256;
    private static final int LOCATION_FETCH_BATCH_SIZE = 20;
    private final DecoraAPIs apis;

    public GetDecoraProductsServiceImpl(DecoraAPIs apis) {
        this.apis = apis;
    }
    /**
     * Fetches the products matching the query & subcategory using decora.ee search API
     * @param keyword Query to search products with
     * @param subcategory Ematerjal.ee subcategory
     * @return Returns a list of ProductDto, with every field except for LocationDto filled
     */
    private List<ProductParseDto> performDecoraSearch(String keyword, Subcategory subcategory) {
        int offset = 0;
        int numProducts = 0;
        List<ProductParseDto> products = new ArrayList<>();

        do {
            JSONObject responseJson = apis.fetchPageFromSearchAPI(keyword, subcategory, offset);
            if (responseJson == null && numProducts == 0) {
                System.err.println("Decora products service: very first search API request failed, cannot continue fetching products");
                break;
            }
            if (responseJson == null) { // we should still continue with the rest of the request even if part of products are missing
                offset += DecoraAPIs.SEARCH_API_PAGE_SIZE;
                continue;
            }

            JSONObject meta = responseJson.getJSONArray("queryResults").getJSONObject(0).getJSONObject("meta");
            JSONArray productsArr = responseJson.getJSONArray("queryResults").getJSONObject(0).getJSONArray("records");
            numProducts = Math.min(meta.getInt("totalResultsFound"), FETCH_MAX_NUM_PRODUCTS);
            offset += DecoraAPIs.SEARCH_API_PAGE_SIZE;

            for(int i = 0; i < productsArr.length(); i++) {
                JSONObject productJson = productsArr.getJSONObject(i);
                String productName =productJson.getString("name");
                if (keyword != null && !checkProductNameCorrespondsToSearch(productName, keyword)) continue;
                if ((keyword == null || keyword.isEmpty()) && !checkContainsRequiredKeyword(productName, subcategory)) continue;

                // instock check won't work here, it is only for web store but physical stores can still have stock
                try {
                    ProductDto product = ProductDto.builder()
                            .store(Store.DECORA)
                            .name(productJson.getString("name"))
                            .price(productJson.getDouble("price"))
                            .unit(Unit.fromDisplayName(productJson.getString("unit")))
                            .linkToPicture(productJson.getString("imageUrl"))
                            .linkToProduct(productJson.getString("url"))
                            .build();

                    ProductParseDto productIntermediateInfo = ProductParseDto.builder()
                            .product(product)
                            .searchApiProductInfo(productJson.toString())
                            .build();

                    products.add(productIntermediateInfo);
                } catch(org.json.JSONException e) {
                    System.err.println("Decora products service: Error getting certain values from JSON for product: " + e.getMessage());
                    System.err.println(productJson);
                } catch(IllegalArgumentException e) {
                    System.err.println("Decora products service: " + e.getMessage());
                    System.err.println(productJson.getString("url"));
                }
            }
        } while(offset < numProducts);

        return products;
    }

    /**
     * Fetches stock & location info for each product. Requests are sent in a batch
     * @param products Products parsed so far from the search API
     * @return An updated list of products with
     */
    private List<ProductParseDto> fetchLocationInfo(List<ProductParseDto> products) {
        List<ProductParseDto> newProducts = new ArrayList<>();
        List<List<ProductParseDto>> batches = Lists.partition(products, LOCATION_FETCH_BATCH_SIZE);

        for(List<ProductParseDto> batch : batches) {
            List<CompletableFuture<Document>> futures = new ArrayList<>();
            for(ProductParseDto productParseInfo : batch) {
                JSONObject productJson = new JSONObject(productParseInfo.getSearchApiProductInfo());

                futures.add(
                        apis.fetchLocationInfoForProduct(productJson.getString("sku"))
                );
            }

            for(int i = 0; i < futures.size(); i++) {
                CompletableFuture<Document> future = futures.get(i);
                ProductParseDto productParseInfo = batch.get(i);

                try {
                    Document locationsHtml = future.join();
                    ProductDto product = productParseInfo.getProduct();
                    product = product.toBuilder()
                            .stock(mapLocationWithStock(locationsHtml))
                            .build();
                    productParseInfo.setProduct(product);
                } catch(CompletionException e) {
                    System.err.printf("ESPAK products service: Error fetching data from URL: %s, Exception: %s\n", productParseInfo.getProduct().getLinkToProduct(), e.getMessage());
                } finally {
                    newProducts.add(productParseInfo);
                }
            }
        }

        return newProducts;
    }

    /**
     * Assigns stock to each store location available. If locationsHtml doesn't have the location, then we will assign 'Info unavailable' to that store.
     * @param locationsHtml HTML document containing location info
     * @return List of locations with stock info for the store
     */
    private StockInLocationsDto mapLocationWithStock(Document locationsHtml) {
        HashMap<String, String> stockForLoc = new HashMap<>();
        Elements trs = locationsHtml.select("tr.store-row");
        for (Element tr : trs) {
            String location = tr.select("td.store-name").text(); // formatted as: city, street addr
            try {
                String quantity = String.format("%s tk", tr.select("td.store-stock").text());
                stockForLoc.put(location, quantity);
            } catch (NumberFormatException e) {
                System.err.printf("ESPAK products service: Error parsing quantity for location %s, Exception: %s\n", location, e.getMessage());
            }
        }

        List<StockDto> stockInfo = new ArrayList<>();
        for (DecoraStoreLocation store : DecoraStoreLocation.values()) {
            String displayedLocation = String.format("%s, %s", store.getLocation().getLocationName().getDisplayName(), store.getLocation().getAddress());

            stockInfo.add(
                    StockDto.builder()
                            .location(store.getLocation())
                            .quantityText(stockForLoc.getOrDefault(displayedLocation, "Teadmata"))
                            .build()
            );
        }

        return StockInLocationsDto.builder()
                .locations(stockInfo)
                .build();
    }

    @Override
    public ProductsDto searchForProducts(String query, Subcategory subcategory) {
        List<ProductParseDto> products = performDecoraSearch(query, subcategory);
        products = fetchLocationInfo(products);

        return ProductsDto.builder()
                .products(products.stream().map(ProductParseDto::getProduct).toList())
                .build();
    }
}

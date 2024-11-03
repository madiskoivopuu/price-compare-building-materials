package priceCompare.backend.stores.espak.service;

import static priceCompare.backend.utils.ProductNameChecker.checkProductNameCorrespondsToSearch;

import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import priceCompare.backend.HttpClient.HttpClientService;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.dto.LocationsDto;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
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
    private static final Logger logger = LoggerFactory.getLogger(GetEspakProductsServiceImpl.class);
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

    private List<ProductDto> performEspakSearch(String query) {
        int page = 1;
        List<ProductDto> products = new ArrayList<>();
        boolean moreProductsToFetch = true;

        do {
            long batchStartTime = System.currentTimeMillis();

            List<CompletableFuture<HttpResponse<String>>> searchPageResponses = new ArrayList<>();
            for (int i = 0; i < SEARCH_BATCH_SIZE; i++) {
                URI pageUri = formatSearchUri(query, page + i);
                logger.info("Fetching Espak search page: {}", pageUri);
                searchPageResponses.add(httpClientService.GetStringAsync(pageUri));
            }

            for (CompletableFuture<HttpResponse<String>> responseFuture : searchPageResponses) {
                page++;
                HttpResponse<String> response;

                try {
                    response = responseFuture.join();
                    logger.info("Received response for page {} with status code: {}", page, response.statusCode());
                } catch (CompletionException e) {
                    logger.error("Error fetching data from search page {}: {}", page, e.getMessage());
                    continue;
                }

                if (response.statusCode() == 404) {
                    moreProductsToFetch = false;
                    logger.info("No more products to fetch; reached page 404.");
                    break;
                }

                String htmlPage = response.body();
                logger.debug("Response body for page {}: {}", page, htmlPage.substring(0, Math.min(htmlPage.length(), 500))); // Log only the first 500 characters

                Document doc = Jsoup.parse(htmlPage);
                Elements productElements = doc.select("div.product.type-product");

                if (productElements.isEmpty()) {
                    logger.warn("No product elements found on page {}", page);
                    continue;
                }

                for (Element productElement : productElements) {
                    if (!productElement.select("p.listing-stock-in").text().equals("Laos")) {
                        logger.debug("Product out of stock on page {}: {}", page, productElement);
                        continue;
                    }

                    String productName = productElement.select("p.name.product-title > a").text();
                    if (!checkProductNameCorrespondsToSearch(productName, query)) {
                        logger.debug("Product name does not match search query on page {}: {}", page, productName);
                        continue;
                    }

                    try {
                        double price = Double.parseDouble(productElement.select("div.price-wrapper > div:nth-child(1)").text().replace("€", "").trim());
                        ProductDto product = ProductDto.builder()
                                .store(Store.ESPAK)
                                .name(productName)
                                .price(price)
                                .linkToProduct(productElement.select("p.name.product-title > a").attr("href"))
                                .linkToPicture(productElement.select("img").attr("src"))
                                .location(mapLocationWithStock())
                                .build();
                        products.add(product);
                        logger.info("Added product: {}", productName);
                    } catch (Exception e) {
                        logger.error("Error parsing product element on page {}: {}", page, e.getMessage());
                    }
                }
            }

            long batchEndTime = System.currentTimeMillis();
            logger.info("Batch of {} pages took {} ms", SEARCH_BATCH_SIZE, (batchEndTime - batchStartTime));

        } while (products.size() < FETCH_MAX_NUM_PRODUCTS && moreProductsToFetch);

        logger.info("Finished fetching Espak products. Total products found: {}", products.size());
        return products;
    }

    private List<ProductDto> fetchCoreInfoFields(List<ProductDto> products) {
        List<ProductDto> newProducts = new ArrayList<>();
        List<List<ProductDto>> batches = Lists.partition(products, PRODUCT_BATCH_SIZE);

        for (List<ProductDto> batch : batches) {
            long batchStartTime = System.currentTimeMillis();

            List<CompletableFuture<String>> responseFutures = new ArrayList<>();
            for (ProductDto product : batch) {
                URI productUri = URI.create(product.getLinkToProduct());
                logger.info("Fetching detailed info for product: {}", productUri);
                responseFutures.add(httpClientService.GetStringAsync(productUri).thenApply(HttpResponse::body));
            }

            for (int i = 0; i < batch.size(); i++) {
                CompletableFuture<String> future = responseFutures.get(i);
                ProductDto product = batch.get(i);

                try {
                    String resp = future.join();
                    Document body = Jsoup.parse(resp);
                    String unitText = body.select("div.unit").text();
                    product = product.toBuilder().unit(Unit.fromDisplayName(unitText)).build();
                    logger.info("Updated product with detailed info: {}", product.getName());
                } catch (CompletionException e) {
                    logger.error("Error fetching data from URL {}: {}", product.getLinkToProduct(), e.getMessage());
                } finally {
                    newProducts.add(product);
                }
            }

            long batchEndTime = System.currentTimeMillis();
            logger.info("Batch of {} products took {} ms to fetch detailed information", PRODUCT_BATCH_SIZE, (batchEndTime - batchStartTime));
        }

        logger.info("Finished fetching detailed info for Espak products. Total products enriched: {}", newProducts.size());
        return newProducts;
    }

    private LocationsDto mapLocationWithStock() {
        List<LocationDto> locations = new ArrayList<>();
        for (EspakStoreLocation storeLocation : EspakStoreLocation.values()) {
            if (storeLocation.location.getLocationName() == LocationName.TALLINN) {
                locations.add(
                        storeLocation.location.toBuilder()
                                .quantity(-1)
                                .infoUnavailable(false)
                                .additionalNotes("ESPAKi veebipood näitab toodete kättesaadavust Tallinna poes.")
                                .build()
                );
            } else {
                locations.add(
                        storeLocation.location.toBuilder()
                                .quantity(0)
                                .infoUnavailable(true)
                                .build()
                );
            }
        }

        return LocationsDto.builder()
                .locations(locations)
                .build();
    }

    @Override
    public ProductsDto searchForProducts(String query, Category category, Subcategory subcategory) {
        List<ProductDto> products = performEspakSearch(query);
        products = fetchCoreInfoFields(products);

        logger.info("Completed product search for Espak with query '{}'. Total products returned: {}", query, products.size());
        return ProductsDto.builder().products(products).build();
    }
}

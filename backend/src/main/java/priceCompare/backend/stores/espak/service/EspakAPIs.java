package priceCompare.backend.stores.espak.service;

import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import priceCompare.backend.httpclient.HttpClientService;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.utils.UserInputEscaper;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class EspakAPIs {
    private final HttpClientService httpClientService;

    public static final String NO_CATEGORY = "olematukategooria";
    private static final String SEARCH_API_URL = "https://utkuha7jt0-dsn.algolia.net/1/indexes/*/queries?x-algolia-agent=Algolia%20for%20JavaScript%20(4.10.5)%3B%20Browser%20(lite)%3B%20autocomplete-core%20(1.17.7)%3B%20autocomplete-js%20(1.17.7)&x-algolia-api-key=b9c55a35ad2799389ce3d4584a5f9def&x-algolia-application-id=UTKUHA7JT0";
    private static final String SEARCH_API_REQUEST = """
            {
              "requests": [
                {
                  "indexName": "vwwm_posts_product",
                  "query": "%s",
                  "params": "page=%d&hitsPerPage=100&clickAnalytics=true&facetFilters=%s"
                }
              ]
            }
            """;

    /**
     * Formats filters for the ESPAK search API
     * @param subcategory Ematerjal.ee subcategory
     * @return Filters, which can be used limit the search results to only have mapped category products in them.
     */
    public String formatFacetFilters(Subcategory subcategory) {
        StringBuilder facetFilter = new StringBuilder("[\"stock_status:in-stock\"");

        if(subcategory != null) {
            List<String> categoryMappings = EmaterjalToEspakCategoryMapping.subcatMap.getOrDefault(subcategory, List.of());

            if(!categoryMappings.isEmpty()) {
                facetFilter.append(", [\"");
                facetFilter.append(
                        String.join(
                                "\", \"",
                                categoryMappings.stream().map(str -> String.format("taxonomies.product_cat:%s", str)).toList()
                        )
                );
                facetFilter.append("\"]");
            } else {
                facetFilter.append(String.format(", \"taxonomies.product_cat:%s\"", NO_CATEGORY)); // no products will be returned since this category does not existt
            }
        }

        facetFilter.append("]");
        return facetFilter.toString();
    }

    /**
     * Fetch one page of results from the search API.
     * @param query Keywords used in the search
     * @param subcategory Ematerjal.ee subcategory to filter products with
     * @param page Number of the page we want to fetch
     * @return The products fetched from the given page
     */
    public JSONObject fetchPageFromSearchAPI(String query, Subcategory subcategory, int page) {
        String facetFilters = formatFacetFilters(subcategory);
        String reqBody = String.format(SEARCH_API_REQUEST, UserInputEscaper.escapeForJson(query), page, URLEncoder.encode(facetFilters, StandardCharsets.UTF_8));

        return httpClientService.PostWithBodyAndReturnJson(URI.create(SEARCH_API_URL), reqBody);
    }

    /**
     * Fetches the product page for an ESPAK product
     * @param productLink Link to the HTML page for the product.
     * @return A future which will return the parsed HTML document with product info
     */
    public CompletableFuture<Document> fetchProductPage(String productLink) {
        return httpClientService.GetAsyncAndReturnCompletableFutureHttpResponse(URI.create(productLink))
                .thenApply(HttpResponse::body)
                .thenApply(Jsoup::parse);
    }
}

package priceCompare.backend.stores.decora.service;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import priceCompare.backend.HttpClient.HttpClientService;
import priceCompare.backend.enums.Subcategory;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@Service
public class DecoraAPIs {
    public static final int SEARCH_API_PAGE_SIZE = 100;

    private final HttpClientService httpClientService;
    public DecoraAPIs(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    /**
     * Fetch one page of results from the search API.
     * @param query Keywords used in the search
     * @param subcategory Ematerjal.ee subcategory
     * @param offset The amount of products we want to skip from the search result. Search API always gives products in the same order.
     * @return The products fetched from offset to the page limit
     */
    public JSONObject fetchPageFromSearchAPI(String query, Subcategory subcategory, int offset) {
        String body = String.format("""
                {
                  "context": {
                    "apiKeys": [
                      "klevu-159479682665411675"
                    ]
                  },
                  "recordQueries": [
                    {
                      "id": "productList",
                      "typeOfRequest": "SEARCH",
                      "settings": {
                        "query": {
                          "term": "%s"
                        },
                        "typeOfRecords": [
                          "KLEVU_PRODUCT"
                        ],
                        "limit": %d,
                        "offset": %d,
                        "typeOfSearch": "AND",
                        "searchPrefs": [
                          "searchCompoundsAsAndQuery"
                        ],
                        "sort": "RELEVANCE",
                        "fallbackQueryId": "productListFallback"
                      },
                      "filters": {
                        "filtersToReturn": {
                          "enabled": true,
                          "rangeFilterSettings": [
                            {
                              "key": "klevu_price",
                              "minMax": "true"
                            }
                          ]
                        }
                      }
                    }
                  ]
                }
                """, query, SEARCH_API_PAGE_SIZE, offset);

        return httpClientService.PostWithBody(URI.create("https://decoracsv2.ksearchnet.com/cs/v2/search"), body);
    }

    /**
     * Fetches location info for a product in decora.ee
     * @param sku A unique identifier (stock keeping unit) that the decora.ee search API returns for each product.
     * @return A future which will return the parsed HTML document with location & stock info
     */
    public CompletableFuture<Document> fetchLocationInfoForProduct(String sku) {
        URI locationUri = URI.create(String.format("https://www.decora.ee/stockavailability/load/stock/?product_sku=%s&_=%d", sku, System.currentTimeMillis()));
        return httpClientService.GetStringAsync(locationUri)
                .thenApply(HttpResponse::body)
                .thenApply(Jsoup::parse);
    }
}

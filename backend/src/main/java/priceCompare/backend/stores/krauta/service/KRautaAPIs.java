package priceCompare.backend.stores.krauta.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import priceCompare.backend.HttpClient.HttpClientService;
import priceCompare.backend.enums.Subcategory;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class KRautaAPIs {
    public static final String QUERY_KEY_FETCH_PRODUCTS = "NSykN7XLeh4CPAauPZ4TAvVuFUjPt7QY";
    public static final int SEARCH_API_PAGE_SIZE = 72;

    private final HttpClientService httpClientService;
    public KRautaAPIs(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    public URI formatSearchUrl(String query, Subcategory subcategory, int offset) {
        return URI.create(String.format("https://sd.searchnode.net/v1/query/docs?query_key=%s" +
                "&search_query=%s" +
                "&offset=%d" +
                "&facet_filters.categories1=Ehitus+ja+remont" +
                "&facet_filters.categories1=Viimistlusmaterjalid" +
                "&limit=%d", QUERY_KEY_FETCH_PRODUCTS, URLEncoder.encode(query, StandardCharsets.UTF_8), offset, SEARCH_API_PAGE_SIZE));
    }

    /**
     * Fetch one page of results from the search API.
     * @param query Keywords used in the search
     * @param subcategory Ematerjal.ee subcategory
     * @param offset The amount of products we want to skip from the search result. Search API always gives products in the same order.
     * @return The products fetched from offset to the page limit
     */
    public JSONObject fetchPageFromSearchAPI(String query, Subcategory subcategory, int offset) {
        URI uri = formatSearchUrl(query, subcategory, offset);
        return httpClientService.GetJson(uri);
    }
}

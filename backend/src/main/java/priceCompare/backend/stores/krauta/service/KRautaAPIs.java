package priceCompare.backend.stores.krauta.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priceCompare.backend.HttpClient.HttpClientService;
import priceCompare.backend.enums.Subcategory;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class KRautaAPIs {
    public static final String QUERY_KEY_FETCH_PRODUCTS = "NSykN7XLeh4CPAauPZ4TAvVuFUjPt7QY";
    public static final int SEARCH_API_PAGE_SIZE = 72;

    @Autowired
    private HttpClientService httpClientService;

    public URI formatSearchUrl(String query, Subcategory subcategory, int offset) {
        // TODO: map ematerjal.ee categories to krauta
        String queryString = "query_key=" + QUERY_KEY_FETCH_PRODUCTS
                + "&search_query=" + URLEncoder.encode(query, StandardCharsets.UTF_8)
                + "&offset=" + offset
                + "&limit=" + SEARCH_API_PAGE_SIZE;
        return URI.create("https://sd.searchnode.net/v1/query/docs?" + queryString);
    }

    /**
     * Fetch one page of results from the search API.
     * @param query Keywords used in the search
     * @param subcategory Ematerjal.ee subcategory
     * @param offset The amount of products we want to skip from the search result. Search API always gives products in the same order.
     * @return The products fetched from offset to the page limit (48)
     */
    public JSONObject fetchPageFromSearchAPI(String query, Subcategory subcategory, int offset) {
        URI uri = formatSearchUrl(query, subcategory, offset);
        return httpClientService.Get(uri);
    }
}

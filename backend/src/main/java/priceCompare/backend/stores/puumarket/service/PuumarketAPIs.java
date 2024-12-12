package priceCompare.backend.stores.puumarket.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import priceCompare.backend.httpclient.HttpClientService;
import priceCompare.backend.enums.Subcategory;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
@AllArgsConstructor
@Log4j2
public class PuumarketAPIs {
    private final String SEARCH_URL = "https://puumarket.ee/";

    private final HttpClientService httpClientService;

    private String formatSearchParams(String query) {
        return String.format("?s=%s&src=product", URLEncoder.encode(query, StandardCharsets.UTF_8));
    }

    private List<Document> fetchCategoryPages(Subcategory subcategory) {
        List<Document> categoryPages = new ArrayList<>();

        List<String> mappedUrls = EmaterjalToPuumarketCategoryMapping.categoryMap.getOrDefault(subcategory, List.of());
        if(mappedUrls.isEmpty()) return categoryPages;

        List<CompletableFuture<Document>> futures = new ArrayList<>();
        for(String categoryUrl : mappedUrls) {
            futures.add(
                    httpClientService.GetAsyncAndReturnCompletableFutureHttpResponse(URI.create(categoryUrl))
                            .thenApply(HttpResponse::body)
                            .thenApply(Jsoup::parse)
            );
        }

        for(CompletableFuture<Document> future : futures) {
            try {
                Document doc = future.join();
                categoryPages.add(doc);
            } catch (CompletionException e) {
                log.printf(Level.WARN, "PuumarketAPIs: Error fetching category products, Exception: %s\n", e.getMessage());
            }
        }

        return categoryPages;
    }
    
    private Document fetchSearchResults(String query) {
        String searchUrl = SEARCH_URL + formatSearchParams(query);
        String resHtml = httpClientService.GetAndReturnString(URI.create(searchUrl));
        if(resHtml == null) return null;

        return Jsoup.parse(resHtml);
    }

    public List<Document> performSearchOrCategoryPageFetch(String query, Subcategory subcategory) {
        // puumarket.ee doesn't let us perform query search on a category, only on a general range of products
        List<Document> results = new ArrayList<>();
        if(subcategory != null) {
            results = fetchCategoryPages(subcategory);
        } else {
            Document res = fetchSearchResults(query);
            if(res != null) results.add(fetchSearchResults(query));
        }

        return results;
    }
}

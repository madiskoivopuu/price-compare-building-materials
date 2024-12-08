package priceCompare.backend.HttpClient;

import org.springframework.stereotype.Service;
import priceCompare.backend.utils.Cache;

import java.net.URI;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@Service
public class CachingHttpClientService extends HttpClientService {
    private final int MAX_MINUTES_BETWEEN_CLEANUPS = 2;
    private final int DEFAULT_CACHE_TIME_MINUTES = 90;
    private final Cache<String, HttpResponse<String>> cachedResults = new Cache<>(MAX_MINUTES_BETWEEN_CLEANUPS, DEFAULT_CACHE_TIME_MINUTES);

    public void ClearFromCache(String url) {
        cachedResults.remove(url);
    }

    public CompletableFuture<HttpResponse<String>> GetAsyncCached(String url, Duration cacheTime) {
        HttpResponse<String> cachedResp = cachedResults.get(url);
        if (cachedResp != null && !cachedResp.body().isEmpty()) {
            CompletableFuture<HttpResponse<String>> future = new CompletableFuture<>();
            future.complete(cachedResp);
            return future;
        } else {
            CompletableFuture<HttpResponse<String>> future = this.GetAsyncAndReturnCompletableFutureHttpResponse(URI.create(url));
            future.thenApply((r) -> {
                if (r != null && !r.body().isEmpty()) {
                    cachedResults.put(url, r, cacheTime);
                }
                return r;
            });
            return future;
        }
    }

    public CompletableFuture<HttpResponse<String>> GetAsyncCached(String url) {
        return this.GetAsyncCached(url, Duration.ofMinutes(DEFAULT_CACHE_TIME_MINUTES));
    }
}

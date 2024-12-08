package priceCompare.backend.service;


import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priceCompare.backend.HttpClient.CachingHttpClientService;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.utils.Cache;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class SearchCachingService {
    private final int MAX_MINUTES_BETWEEN_CLEANUPS = 5;
    private final int MAX_MINUTES_CACHE_SEARCH = 100;
    private final int CONCURRENT_CACHE_REQUESTS = 20;

    private final Cache<String, ProductsDto> cachedSearchResults = new Cache<>(MAX_MINUTES_BETWEEN_CLEANUPS, MAX_MINUTES_CACHE_SEARCH);

    private CachingHttpClientService cachingHttpClient;

    public SearchCachingService() {
        Thread prodPageFetchThread = new Thread(this::autoCacheProducts);
        prodPageFetchThread.start();
    }

    @Autowired
    public SearchCachingService(CachingHttpClientService cachingHttpClient) {
        this();

        this.cachingHttpClient = cachingHttpClient;
    }

    @SneakyThrows
    private void autoCacheProducts() {
        while(true) {
            List<String> cachePages = new ArrayList<>();
            for(String searchCacheKey : cachedSearchResults.keySet()) {
                for(ProductDto product : cachedSearchResults.get(searchCacheKey).getProducts()) {
                    cachePages.add(product.getLinkToProduct());
                }
            }

            List<List<String>> productUrlsBatches = Lists.partition(cachePages, CONCURRENT_CACHE_REQUESTS);
            for(List<String> batch : productUrlsBatches) {
                List<CompletableFuture<HttpResponse<String>>> futures = new ArrayList<>();
                for(String productUrl : batch)
                    futures.add(cachingHttpClient.GetAsyncCached(productUrl));

                for(CompletableFuture<HttpResponse<String>> future : futures) {
                    try {
                        future.join();
                    } catch(CompletionException e) {
                        // do nothing
                    }
                }
            }

            Thread.sleep(1000);
        }
    }

    public void addProductsToCache(String searchId, ProductsDto productsDto) {
        cachedSearchResults.put(searchId, productsDto);
    }

    public ProductsDto getSearchResultFromCache(String searchId) {
        if(searchId == null)
            return null;

        return cachedSearchResults.get(searchId);
    }
}

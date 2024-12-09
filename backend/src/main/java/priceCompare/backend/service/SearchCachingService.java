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

    private final Cache<String, ProductsDto> cachedSearchResults = new Cache<>(MAX_MINUTES_BETWEEN_CLEANUPS, MAX_MINUTES_CACHE_SEARCH);

    public void addProductsToCache(String searchId, ProductsDto productsDto) {
        cachedSearchResults.put(searchId, productsDto);
    }

    public ProductsDto getSearchResultFromCache(String searchId) {
        if(searchId == null)
            return null;

        return cachedSearchResults.get(searchId);
    }
}

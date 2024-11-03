package priceCompare.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.stores.bauhof.service.GetBauhofProductsServiceImpl;
import priceCompare.backend.stores.espak.service.GetEspakProductsServiceImpl;
import priceCompare.backend.stores.krauta.service.GetKRautaProductsServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Service
public class FindProductsServiceImpl implements FindProductService {
    private static final Logger logger = LoggerFactory.getLogger(FindProductsServiceImpl.class);

    @Autowired
    private GetBauhofProductsServiceImpl getBauhofProductsService;

    @Autowired
    private GetKRautaProductsServiceImpl getKRautaProductsService;

    @Autowired
    private GetEspakProductsServiceImpl getEspakProductsService;

    @Override
    public ProductsDto findProducts(String keyword, Category category, Subcategory subcategory) {
        // Record the start time
        long startTime = System.currentTimeMillis();
        logger.info("Starting concurrent product fetch for Bauhof, Krauta, and Espak services");

        // Start fetching products concurrently for Bauhof, Krauta, and Espak
        CompletableFuture<ProductsDto> bauhofFuture = CompletableFuture.supplyAsync(() ->
                getBauhofProductsService.getBauhofProducts(keyword, category, subcategory)
        );
        CompletableFuture<ProductsDto> krautaFuture = CompletableFuture.supplyAsync(() ->
                getKRautaProductsService.getKRautaProducts(keyword, category, subcategory)
        );
        CompletableFuture<ProductsDto> espakFuture = CompletableFuture.supplyAsync(() ->
                getEspakProductsService.searchForProducts(keyword, category, subcategory)
        );

        ProductsDto products = ProductsDto.builder().products(new ArrayList<>()).build();

        // Wait for all services to complete and combine results
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(bauhofFuture, krautaFuture, espakFuture);

        allFutures.thenRun(() -> {
            List<ProductsDto> fetchedProducts = Stream.of(bauhofFuture, krautaFuture, espakFuture)
                    .map(CompletableFuture::join) // Wait for each future to complete
                    .toList();

            for (ProductsDto fetchedProduct : fetchedProducts) {
                AddFetchedProductsToList(products, fetchedProduct);
            }

            // Log the total time taken
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            logger.info("Concurrent fetch for Bauhof, Krauta, and Espak complete in {} ms. Total products found: {}",
                    duration, products.getProducts().size());
        }).join(); // Wait for all futures to complete

        return products;
    }

    private ProductsDto AddFetchedProductsToList(ProductsDto products, ProductsDto productsToBeAdded) {
        if (productsToBeAdded != null) {
            products.getProducts().addAll(productsToBeAdded.getProducts());
        }
        return products;
    }
}

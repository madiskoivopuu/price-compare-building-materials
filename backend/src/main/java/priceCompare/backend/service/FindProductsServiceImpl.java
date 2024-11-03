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
import java.util.stream.Collectors;
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

        // Start fetching products concurrently for Bauhof and Krauta
        logger.info("Starting concurrent product fetch for Bauhof service");
        CompletableFuture<ProductsDto> bauhofFuture = CompletableFuture.supplyAsync(() ->
                getBauhofProductsService.getBauhofProducts(keyword, category, subcategory)
        );
        logger.info("Starting concurrent product fetch for Krauta service");
        CompletableFuture<ProductsDto> krautaFuture = CompletableFuture.supplyAsync(() ->
                getKRautaProductsService.getKRautaProducts(keyword, category, subcategory)
        );

        ProductsDto products = ProductsDto.builder().products(new ArrayList<>()).build();

        // wait for Bauhof and Krauta to complete, then add their results
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(bauhofFuture, krautaFuture);

        allFutures.thenRun(() -> {
            List<ProductsDto> fetchedProducts = Stream.of(bauhofFuture, krautaFuture)
                    .map(CompletableFuture::join) // wait for each future to complete
                    .toList();

            for (ProductsDto fetchedProduct : fetchedProducts) {
                AddFetchedProductsToList(products, fetchedProduct);
            }

            logger.info("Concurrent fetch for Bauhof and Krauta complete. Total products so far: {}", products.getProducts().size());
        }).join(); // wait for Bauhof and KRauta to complete

        // Run the Espak service synchronously after Bauhof and KRauta
        logger.info("Fetching products from Espak service synchronously");
        ProductsDto espakProducts = getEspakProductsService.searchForProducts(keyword, category, subcategory);
        AddFetchedProductsToList(products, espakProducts);

        logger.info("All product fetch operations complete. Total products found: {}", products.getProducts().size());

        return products;
    }


    private ProductsDto AddFetchedProductsToList(ProductsDto products, ProductsDto productsToBeAdded) {
        if (productsToBeAdded != null) {
            products.getProducts().addAll(productsToBeAdded.getProducts());
        }
        return products;
    }
}

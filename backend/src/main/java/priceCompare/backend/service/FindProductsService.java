package priceCompare.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Service;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.stores.GetStoreProductsService;
import priceCompare.backend.stores.bauhaus.service.GetBauhausProductsService;
import priceCompare.backend.stores.bauhof.service.GetBauhofProductsService;
import priceCompare.backend.stores.decora.service.GetDecoraProductsService;
import priceCompare.backend.stores.ehituseabc.service.GetEhituseAbcProductsService;
import priceCompare.backend.stores.ehomer.service.GetEhomerProductsService;
import priceCompare.backend.stores.espak.service.GetEspakProductsService;
import priceCompare.backend.stores.krauta.service.GetKRautaProductsService;
import priceCompare.backend.stores.puumarket.service.GetPuumarketProductsService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
@Log4j2
public class FindProductsService {
    private GetBauhofProductsService getBauhofProductsService;
    private GetKRautaProductsService getKRautaProductsService;
    private GetEspakProductsService getEspakProductsService;
    private GetDecoraProductsService getDecoraProductsService;
    private GetPuumarketProductsService getPuumarketProductsService;
    private GetEhituseAbcProductsService getEhituseAbcProductsService;
    private GetEhomerProductsService getEhomerProductsService;
    private GetBauhausProductsService getBauhausProductsService;
    private SearchCachingService cachingService;

    public ProductsDto findProducts(String keyword, Subcategory subcategory) {
        ProductsDto products = ProductsDto.builder().products(new ArrayList<>()).build();

        // Create a list of CompletableFutures for asynchronous execution
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        futures.add(fetchAndAddProducts(products, getBauhofProductsService, keyword, subcategory, "bauhof"));
        futures.add(fetchAndAddProducts(products, getKRautaProductsService, keyword, subcategory, "krauta"));
        futures.add(fetchAndAddProducts(products, getEspakProductsService, keyword, subcategory, "espak"));
        futures.add(fetchAndAddProducts(products, getDecoraProductsService, keyword, subcategory, "decora"));
        futures.add(fetchAndAddProducts(products, getPuumarketProductsService, keyword, subcategory, "puumarket"));
        futures.add(fetchAndAddProducts(products, getEhituseAbcProductsService, keyword, subcategory, "EhituseABC"));
        futures.add(fetchAndAddProducts(products, getEhomerProductsService, keyword, subcategory, "Ehomer"));
        futures.add(fetchAndAddProducts(products, getBauhausProductsService, keyword, subcategory, "bauhaus"));

        // Wait for all futures to complete
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        try {
            allOf.get(); // Blocks until all are complete
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while fetching products", e);
        }

        String searchId = UUID.randomUUID().toString();
        cachingService.addProductsToCache(searchId, products);

        products = products.toBuilder()
                .searchId(searchId)
                .build();

        return products;
    }

    private CompletableFuture<Void> fetchAndAddProducts(ProductsDto products, GetStoreProductsService service, String keyword, Subcategory subcategory, String storeName) {
        return CompletableFuture.runAsync(() -> {
            long startTime = System.currentTimeMillis();
            ProductsDto fetchedProducts = service.searchForProducts(keyword, subcategory);
            long endTime = System.currentTimeMillis();
            long duration = (endTime - startTime) / 1000;
            log.printf(Level.INFO,"%s - Time taken: %d seconds" ,storeName, duration);

            // Add fetched products to the main product list
            synchronized (products) {
                AddFetchedProductsToList(products, fetchedProducts);
            }
        });
    }

    private ProductsDto AddFetchedProductsToList(ProductsDto products, ProductsDto productsToBeAdded) {
        if (productsToBeAdded != null && productsToBeAdded.getProducts() != null) {
            products.getProducts().addAll(productsToBeAdded.getProducts());
        }
        return products;
    }
}

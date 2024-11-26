package priceCompare.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.stores.GetStoreProductsService;
import priceCompare.backend.stores.bauhof.service.GetBauhofProductsServiceImpl;
import priceCompare.backend.stores.decora.service.GetDecoraProductsServiceImpl;
import priceCompare.backend.stores.ehituseabc.service.GetEhituseAbcProductsServiceImpl;
import priceCompare.backend.stores.ehomer.service.GetEhomerProductsServiceImpl;
import priceCompare.backend.stores.espak.service.GetEspakProductsServiceImpl;
import priceCompare.backend.stores.krauta.service.GetKRautaProductsServiceImpl;
import priceCompare.backend.stores.puumarket.service.GetPuumarketProductsServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class FindProductsServiceImpl implements FindProductService {

    @Autowired
    private GetBauhofProductsServiceImpl getBauhofProductsService;

    @Autowired
    private GetKRautaProductsServiceImpl getKRautaProductsService;

    @Autowired
    private GetEspakProductsServiceImpl getEspakProductsService;

    @Autowired
    private GetDecoraProductsServiceImpl getDecoraProductsService;

    @Autowired
    private GetPuumarketProductsServiceImpl getPuumarketProductsService;

    @Autowired
    private GetEhituseAbcProductsServiceImpl getEhituseAbcProductsService;

    @Autowired
    private GetEhomerProductsServiceImpl getEhomerProductsService;

    @Override
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

        // Wait for all futures to complete
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        try {
            allOf.get(); // Blocks until all are complete
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while fetching products", e);
        }

        return products;
    }

    private CompletableFuture<Void> fetchAndAddProducts(ProductsDto products, GetStoreProductsService service, String keyword, Subcategory subcategory, String storeName) {
        return CompletableFuture.runAsync(() -> {
            long startTime = System.currentTimeMillis();
            ProductsDto fetchedProducts = service.searchForProducts(keyword, subcategory);
            long endTime = System.currentTimeMillis();
            long duration = (endTime - startTime) / 1000;
            System.out.println(storeName + " - Time taken: " + duration + " seconds");

            // Add fetched products to the main product list
            synchronized (products) {
                AddFetchedProductsToList(products, fetchedProducts);
            }
        });
    }

    private ProductsDto AddFetchedProductsToList(ProductsDto products, ProductsDto productsToBeAdded) {
        if (productsToBeAdded != null) {
            products.getProducts().addAll(productsToBeAdded.getProducts());
        }
        return products;
    }
}

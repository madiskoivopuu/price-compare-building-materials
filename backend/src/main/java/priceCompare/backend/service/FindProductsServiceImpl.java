package priceCompare.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.stores.bauhof.service.GetBauhofProductsServiceImpl;
import priceCompare.backend.stores.decora.service.GetDecoraProductsServiceImpl;
import priceCompare.backend.stores.ehituseabc.service.GetEhituseAbcProductsServiceImpl;
import priceCompare.backend.stores.ehomer.service.GetEhomerProductsServiceImpl;
import priceCompare.backend.stores.espak.service.GetEspakProductsServiceImpl;
import priceCompare.backend.stores.krauta.service.GetKRautaProductsServiceImpl;
import priceCompare.backend.stores.puumarket.service.GetPuumarketProductsServiceImpl;

import java.util.ArrayList;

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

        long startTime, endTime, duration;

        startTime = System.currentTimeMillis();
        AddFetchedProductsToList(products, getBauhofProductsService.searchForProducts(keyword, subcategory));
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        System.out.println("bauhof - Time taken: " + duration + " seconds");

        startTime = System.currentTimeMillis();
        AddFetchedProductsToList(products, getKRautaProductsService.searchForProducts(keyword, subcategory));
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        System.out.println("krauta - Time taken: " + duration + " seconds");

        startTime = System.currentTimeMillis();
        AddFetchedProductsToList(products, getEspakProductsService.searchForProducts(keyword, subcategory));
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        System.out.println("espak - Time taken: " + duration + " seconds");

        startTime = System.currentTimeMillis();
        AddFetchedProductsToList(products, getDecoraProductsService.searchForProducts(keyword, subcategory));
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        System.out.println("decora - Time taken: " + duration + " seconds");

        startTime = System.currentTimeMillis();
        AddFetchedProductsToList(products, getPuumarketProductsService.searchForProducts(keyword, subcategory));
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        System.out.println("puumarket - Time taken: " + duration + " seconds");

        startTime = System.currentTimeMillis();
        AddFetchedProductsToList(products, getEhituseAbcProductsService.searchForProducts(keyword, subcategory));
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        System.out.println("EhituseABC - Time taken: " + duration + " seconds");

        startTime = System.currentTimeMillis();
        AddFetchedProductsToList(products, getEhomerProductsService.searchForProducts(keyword, subcategory));
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        System.out.println("Ehomer - Time taken: " + duration + " seconds");
        return products;
    }

    private ProductsDto AddFetchedProductsToList(ProductsDto products, ProductsDto productsToBeAdded) {
        if (productsToBeAdded != null) {
            products.getProducts().addAll(productsToBeAdded.getProducts());
        }
        return products;
    }

}
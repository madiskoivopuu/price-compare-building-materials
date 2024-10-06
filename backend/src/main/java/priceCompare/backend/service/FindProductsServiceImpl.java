package priceCompare.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.stores.bauhof.service.GetBauhofProductsServiceImpl;
import priceCompare.backend.stores.krauta.service.GetKRautaProductsServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FindProductsServiceImpl implements FindProductService {

    @Autowired
    private GetBauhofProductsServiceImpl getBauhofProductsService;

    @Autowired
    private GetKRautaProductsServiceImpl getKRautaProductsService;

    @Override
    public ProductsDto findProducts(String name, Double price, List<String> location, Integer minQuantity, Map<String, String> allParams) {
        ProductsDto products = ProductsDto.builder()
                .products(new ArrayList<>())
                .build();
        AddFetchedProductsToList(products, getBauhofProductsService.getBauhofProducts(name, price, location, minQuantity, allParams));
        AddFetchedProductsToList(products, getKRautaProductsService.getKRautaProducts(name, price, location, minQuantity, allParams));

        return products;
    }

    private ProductsDto AddFetchedProductsToList(ProductsDto products, ProductsDto productsToBeAdded) {
        products.getProducts().addAll(productsToBeAdded.getProducts());
        return products;
    }

}
package priceCompare.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.stores.bauhof.service.GetBauhofProductsServiceImpl;
import priceCompare.backend.stores.krauta.service.GetKRautaProductsServiceImpl;

import java.util.ArrayList;

@Service
public class FindProductsServiceImpl implements FindProductService {

    @Autowired
    private GetBauhofProductsServiceImpl getBauhofProductsService;

    @Autowired
    private GetKRautaProductsServiceImpl getKRautaProductsService;

    @Override
    public ProductsDto findProducts(Category category, Subcategory subcategory) {
        ProductsDto products = ProductsDto.builder().products(new ArrayList<>()).build();
        AddFetchedProductsToList(products, getBauhofProductsService.getBauhofProducts(category, subcategory));
        AddFetchedProductsToList(products, getKRautaProductsService.getKRautaProducts(category, subcategory));
        return products;
    }

    private ProductsDto AddFetchedProductsToList(ProductsDto products, ProductsDto productsToBeAdded) {
        if (productsToBeAdded != null) {
            products.getProducts().addAll(productsToBeAdded.getProducts());
        }
        return products;
    }

}
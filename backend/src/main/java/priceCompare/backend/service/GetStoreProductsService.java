package priceCompare.backend.service;

import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Subcategory;

public interface GetStoreProductsService {
    ProductsDto getStoreProducts(String query, Category category, Subcategory category);
}


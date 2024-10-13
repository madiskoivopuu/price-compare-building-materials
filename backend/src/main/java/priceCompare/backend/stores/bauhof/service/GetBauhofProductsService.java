package priceCompare.backend.stores.bauhof.service;

import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Subcategory;

public interface GetBauhofProductsService {
    ProductsDto getBauhofProducts(String keyword, Category category, Subcategory subcategory);
}
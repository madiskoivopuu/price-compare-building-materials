package priceCompare.backend.stores.krauta.service;

import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Subcategory;

public interface GetKRautaProductsService {
    ProductsDto getKRautaProducts(Category category, Subcategory subcategory);
}
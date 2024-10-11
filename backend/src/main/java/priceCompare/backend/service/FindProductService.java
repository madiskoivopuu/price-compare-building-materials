package priceCompare.backend.service;

import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Subcategory;

public interface FindProductService {
    ProductsDto findProducts(Category category, Subcategory subcategory);
}
package priceCompare.backend.stores;

import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Subcategory;

public interface GetStoreProductsService {
    ProductsDto searchForProducts(String query, Category category, Subcategory subcategory);
}

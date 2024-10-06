package priceCompare.backend.stores.bauhof.service;

import priceCompare.backend.dto.ProductsDto;

import java.util.List;
import java.util.Map;

public interface GetBauhofProductsService {
    ProductsDto getBauhofProducts(String name, Double price, List<String> location, Integer minQuantity, Map<String, String> allParams);
}
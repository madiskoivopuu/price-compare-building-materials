package priceCompare.backend.service;

import priceCompare.backend.dto.ProductsDto;
import java.util.List;
import java.util.Map;

public interface FindProductService {
    ProductsDto findProducts(String name, Double price, List<String> location, Integer minQuantity, Map<String, String> allParams);
}
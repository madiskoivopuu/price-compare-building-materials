package priceCompare.backend.stores.krauta.service;

import priceCompare.backend.dto.ProductsDto;
import java.util.List;
import java.util.Map;

public interface GetKRautaProductsService {
    ProductsDto getKRautaProducts(String name, Double price, List<String> location, Integer minQuantity, Map<String, String> allParams);
}
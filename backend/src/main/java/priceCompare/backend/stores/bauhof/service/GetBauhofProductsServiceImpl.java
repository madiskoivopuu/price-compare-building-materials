package priceCompare.backend.stores.bauhof.service;

import static priceCompare.backend.enums.LocationName.TALLINN;

import org.springframework.stereotype.Service;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Unit;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class GetBauhofProductsServiceImpl implements GetBauhofProductsService {

    @Override
    public ProductsDto getBauhofProducts(String name, Double price, List<String> location, Integer minQuantity, Map<String, String> allParams) {
        //todo replace following hard coded logic with the real one
        LocationDto returnedLocation = LocationDto.builder()
                .locationName(TALLINN)
                .quantity(5)
                .build();

        ProductDto sampleProduct = ProductDto.builder()
                .name("Sample Product")
                .price(19.99)
                .unit(Unit.TK)
                .location(returnedLocation)
                .build();

        return ProductsDto.builder()
                .products(Collections.singletonList(sampleProduct))
                .build();
    }
}
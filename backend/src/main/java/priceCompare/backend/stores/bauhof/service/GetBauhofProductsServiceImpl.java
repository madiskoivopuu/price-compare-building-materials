package priceCompare.backend.stores.bauhof.service;

import static priceCompare.backend.enums.LocationName.TALLINN;

import org.springframework.stereotype.Service;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.enums.Unit;
import java.util.Collections;

@Service
public class GetBauhofProductsServiceImpl implements GetBauhofProductsService {

    @Override
    public ProductsDto getBauhofProducts(Category category, Subcategory subcategory) {
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
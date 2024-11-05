package priceCompare.backend.stores.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import priceCompare.backend.dto.ProductDto;

@Builder
@Getter
@Setter
public class ProductParseDto {
    ProductDto product;
    String searchApiProductInfo; // product info fetched from the search api
    String productPage; // most likmely html, can be some api response
    String locationPage; // html or some api response
}

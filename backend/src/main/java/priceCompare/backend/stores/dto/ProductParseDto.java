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
    String searchApiProductInfo;
    String productPage;
    String locationPage;
    String sku;
    String linkToProduct;
}

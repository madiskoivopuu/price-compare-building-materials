package priceCompare.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Builder
@Getter
@Setter
public class ProductsDto {
    private List<ProductDto> products;
}
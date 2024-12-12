package priceCompare.backend.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Builder(toBuilder = true)
@Getter
public class ProductsDto {
    private String searchId;
    private List<ProductDto> products;
}
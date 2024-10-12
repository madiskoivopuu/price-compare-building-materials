package priceCompare.backend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import priceCompare.backend.enums.Unit;

@Data
@Builder
@Getter
public class ProductDto {
    String name;
    Double price;
    Unit unit;
    String linkToProduct;
    String linkToPicture;
}
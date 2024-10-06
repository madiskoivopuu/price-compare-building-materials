package priceCompare.backend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import priceCompare.backend.enums.Unit;

@Data
@Builder
@Getter
@Setter
public class ProductDto {
    String name;
    Double price;
    Unit unit;
    LocationDto location;
}
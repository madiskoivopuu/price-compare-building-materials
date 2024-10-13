package priceCompare.backend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import priceCompare.backend.enums.Store;
import priceCompare.backend.enums.Unit;

@Data
@Builder
@Getter
public class ProductDto {
    Store store;
    String name;
    Double price;
    Unit unit;
    LocationsDto location;
    String linkToProduct;
    String linkToPicture;
}
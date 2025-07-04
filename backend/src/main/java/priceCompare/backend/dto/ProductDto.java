package priceCompare.backend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import priceCompare.backend.enums.Store;
import priceCompare.backend.enums.Unit;

@Data
@Builder(toBuilder = true)
@Getter
public class ProductDto {
    Store store;
    String name;
    Double price;
    Unit unit;
    StockInLocationsDto stock;
    String linkToProduct;
    String linkToPicture;
}
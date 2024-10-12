package priceCompare.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import priceCompare.backend.enums.LocationName;

@Builder
@Getter
@Setter
public class LocationDto {
    LocationName locationName;
    Integer quantity;
}
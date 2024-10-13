package priceCompare.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import priceCompare.backend.enums.LocationName;

import java.util.List;

@Builder
@Getter
@Setter
public class LocationsDto {
    List<LocationDto> locations;
}
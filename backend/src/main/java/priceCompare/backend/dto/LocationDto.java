package priceCompare.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import priceCompare.backend.enums.LocationName;

@Builder(toBuilder=true)
@Getter
public class LocationDto {
    LocationName locationName;
    String address;
    Integer quantity;
    Boolean infoUnavailable;
    String additionalNotes;
}
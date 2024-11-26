package priceCompare.backend.dto;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder=true)
@Getter
public class StockDto {
    LocationDto location;
    String quantityText;
    String additionalNotes;
}

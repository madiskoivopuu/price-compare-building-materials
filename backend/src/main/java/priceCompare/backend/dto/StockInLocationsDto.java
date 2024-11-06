package priceCompare.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class StockInLocationsDto {
    List<StockDto> locations;
}
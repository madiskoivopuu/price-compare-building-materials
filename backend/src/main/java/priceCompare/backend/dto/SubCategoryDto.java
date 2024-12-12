package priceCompare.backend.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class SubCategoryDto {
    private String displayName;
    private List<FilterDto> filters;
}

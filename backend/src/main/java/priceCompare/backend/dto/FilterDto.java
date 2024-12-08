package priceCompare.backend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FilterDto {
    private String displayName;
    private List<String> keywords;
}

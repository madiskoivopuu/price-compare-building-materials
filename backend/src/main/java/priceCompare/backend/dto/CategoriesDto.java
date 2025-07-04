package priceCompare.backend.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Builder
@Getter
public class CategoriesDto {
    private List<CategoryDto> categories;
}

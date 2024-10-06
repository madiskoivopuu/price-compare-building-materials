package priceCompare.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class CategoriesDto {
    private List<CategoryDto> categories;
}

package priceCompare.backend.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import priceCompare.backend.enums.Category;

@Component
public class StringToCategoryConverter implements Converter<String, Category> {

    @Override
    public Category convert(String source) {
        return Category.fromDisplayName(source);
    }
}
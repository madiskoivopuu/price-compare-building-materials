package priceCompare.backend.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import priceCompare.backend.enums.Subcategory;

@Component
public class StringToSubcategoryConverter implements Converter<String, Subcategory> {

    @Override
    public Subcategory convert(String source) {
        return Subcategory.fromDisplayName(source);
    }
}

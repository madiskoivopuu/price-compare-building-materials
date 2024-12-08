package priceCompare.backend.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import priceCompare.backend.enums.Keyword;

@Component
public class StringToFilterKeywordConverter implements Converter<String, Keyword> {
    @Override
    public Keyword convert(String source) {
        return Keyword.fromDisplayName(source);
    }
}

package priceCompare.backend.utils;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class MetricUnit {
    final String unit;
    final float numericValue;

    final boolean toInt;
    final boolean withWhitespace;
    final String commaValue;
    final int decimalPrecision;

    public String getHumanReadableValue() {
        String whitespace = withWhitespace ? " " : "";
        if(toInt)
            return String.format("%d%s%s", (int)numericValue, whitespace, unit);
        else {
            return String.format("%." + decimalPrecision +"f%s%s", numericValue, whitespace, unit).replace(".", commaValue);
        }
    }
}

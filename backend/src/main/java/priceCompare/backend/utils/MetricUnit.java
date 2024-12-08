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

    public String getHumanReadableValue() {
        String whitespace = withWhitespace ? " " : "";
        if(toInt)
            return String.format("%d%s%s", (int)numericValue, whitespace, unit);
        else {
            return String.format("%.1f%s%s", numericValue, whitespace, unit).replace(".", commaValue);
        }
    }
}

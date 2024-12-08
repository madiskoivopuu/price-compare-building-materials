package priceCompare.backend.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Tuple<A, B> {
    private A first;
    private B second;
}

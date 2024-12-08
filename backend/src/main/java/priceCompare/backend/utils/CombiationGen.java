package priceCompare.backend.utils;

import java.util.ArrayList;
import java.util.List;

public class CombiationGen {
    public static<T> List<Tuple<T, T>> create(List<T> first, List<T> second) {
        List<Tuple<T, T>> result = new ArrayList<Tuple<T, T>>();
        for(T t1 : first)
            for(T t2 : second) {
                result.add(new Tuple<>(t1, t2));
                result.add(new Tuple<>(t2, t1));
            }

        return result;
    }

    public static <T,U> List<Tuple<T, U>> createCombinations(List<T> first, List<U> second) {
        List<Tuple<T, U>> result = new ArrayList<>();
        for(T t1 : first)
            for(U t2 : second) {
                result.add(new Tuple<>(t1, t2));
            }

        return result;
    }

}

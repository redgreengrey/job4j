package ru.job4j.range;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RangeFunction {

    public List<Double> range(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            result.add(func.apply((double) i));
        }
        return result;
    }

    List<Double> linearRange(int start, int end) {
        return range(start, end, (n) -> n);
    }

    List<Double> quadraticRange(int start, int end) {
        return range(start, end, (n) -> Math.pow(n, 2));
    }

    List<Double> logarithmicRange(int start, int end) {
        return range(start, end, Math::log);
    }
}

package ru.job4j.range;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RangeFunctionTest {

    @Test
    public void whenLinear1To5() {
        List<Double> expect = new ArrayList<>(Arrays.asList(1d, 2d, 3d, 4d, 5d));
        List<Double> result = new RangeFunction().range(1, 5, (n) -> n);
        assertThat(result, is(expect));
    }

    @Test
    public void whenQuadratic2To4() {
        List<Double> expect = new ArrayList<>(Arrays.asList(4d, 9d, 16d));
        List<Double> result = new RangeFunction().range(2, 4, (n) -> Math.pow(n, 2));
        assertThat(result, is(expect));
    }

    @Test
    public void whenLogarithmic0To2() {
        List<Double> expect = new ArrayList<>(Arrays.asList(Math.log(0d), Math.log(1d), Math.log(2d)));
        List<Double> result = new RangeFunction().range(0, 2, Math::log);
        assertThat(result, is(expect));
    }
}

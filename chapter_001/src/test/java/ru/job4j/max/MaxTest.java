package ru.job4j.max;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class MaxTest {

    @Test
    public void whenFirstLessSecond() {
        Max maximum = new Max();
        int result = maximum.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenFirstMoreSecond() {
        Max maximum = new Max();
        int result = maximum.max(2, 1);
        assertThat(result, is(2));
    }

    @Test
    public void whenFirstMoreOthers() {
        Max max = new Max();
        int result = max.max(2, 1, 0);
        assertThat(result, is(2));
    }

    @Test
    public void whenSecondMoreOthers() {
        Max max = new Max();
        int result = max.max(2, 4, 0);
        assertThat(result, is(4));
    }

    @Test
    public void whenThirdMoreOthers() {
        Max max = new Max();
        int result = max.max(2, 1, 8);
        assertThat(result, is(8));
    }
}

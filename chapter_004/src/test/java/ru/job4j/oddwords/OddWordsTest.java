package ru.job4j.oddwords;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static ru.job4j.oddwords.OddWords.filter;

public class OddWordsTest {
    @Test
    public void whenOneOddWordThenThereIsNone() {
        String result = filter("Hello fucking user");
        String expect = "Hello user ";
        assertThat(result, is(expect));
    }
}

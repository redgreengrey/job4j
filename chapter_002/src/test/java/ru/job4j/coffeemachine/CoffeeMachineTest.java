package ru.job4j.coffeemachine;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {
    private CoffeeMachine coffeeMachine = new CoffeeMachine();

    @Test
    public void whenValue32AndPrice14ThenResult10521() {
        Integer[] result = {10, 5, 2, 1};
        assertThat(result, is(coffeeMachine.changes(32, 14)));
    }

    @Test
    public void whenValue5AndPrice2ThenResult21() {
        Integer[] res = {2, 1};
        assertThat(res, is(coffeeMachine.changes(5, 2)));
    }

    @Test
    public void whenValue100AndPrice20ThenResult1010101010101010() {
        Integer[] res = {10, 10, 10, 10, 10, 10, 10, 10};
        assertThat(res, is(coffeeMachine.changes(100, 20)));
    }
}

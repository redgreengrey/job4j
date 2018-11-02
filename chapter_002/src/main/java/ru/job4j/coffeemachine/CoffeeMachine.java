package ru.job4j.coffeemachine;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMachine {
    public Integer[] changes(int value, int price) {
        int change = value - price;
        int[] values = {10, 5, 2, 1};
        List<Integer> list = new ArrayList<>();
        if (change > 0) {
            for (int i = 0; i < values.length; i++) {
                while (change >= values[i]) {
                    change -= values[i];
                    list.add(values[i]);
                }
            }
        }
        return list.toArray(new Integer[list.size()]);
    }
}

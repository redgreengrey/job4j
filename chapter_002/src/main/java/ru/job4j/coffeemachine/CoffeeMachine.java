package ru.job4j.coffeemachine;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMachine {
    public int[] changes(int value, int price) {
        int change = value - price;
        int count = 0;
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
        int[] result = new int[list.size()];
        for (int i:list) {
            result[count++] = i;
        }
        return result;
    }
}

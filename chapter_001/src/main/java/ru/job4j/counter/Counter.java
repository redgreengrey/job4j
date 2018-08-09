package ru.job4j.counter;

public class Counter {
    public int add(int start, int finish) {
        int result = 0;
        if (start % 2 != 0) {
            start += 1;
        } else {
            for (int i = start; i <= finish; i = i + 2) {
                result = result + i;
            }
        }

        return result;
    }
}

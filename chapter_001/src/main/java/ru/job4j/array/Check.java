package ru.job4j.array;

public class Check {
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (int i = 0; i < data.length; i++) {
            boolean a = data[0];
            if (a != data[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
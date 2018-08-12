package ru.job4j.array;

public class Check {
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (boolean a : data) {
            for (boolean b : data) {
                if (a != b) {
                    result = false;
                }
            }
        }
        return result;
    }
}
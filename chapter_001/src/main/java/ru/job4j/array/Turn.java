package ru.job4j.array;

public class Turn {
    public int[] turn(int[] array) {
//         temp;
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
//            array[i] = array[array.length - 1];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }
}
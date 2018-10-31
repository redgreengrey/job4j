package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() / rows;
        while (cells * rows < list.size()) {
            cells++;
        }
        int[][] array = new int[rows][cells];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cells; j++) {
                if (index >= list.size()) {
                    array[i][j] = 0;
                } else {
                    array[i][j] = list.get(index++);
                }
            }
        }
        return array;
    }
}
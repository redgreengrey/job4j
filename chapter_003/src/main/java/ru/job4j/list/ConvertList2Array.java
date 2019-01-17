package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells;
        if ((list.size() % rows) == 0) {
            cells = list.size() / rows;
        } else {
            cells = (list.size() / rows) + 1;
        }
        int[][] array = new int[rows][cells];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cells; j++) {
                if (index >= list.size()) {
                    break;
                } else {
                    array[i][j] = list.get(index++);
                }
            }
        }
        return array;
    }

    public List<Integer> convert(List<int[]> list) {
        return list.stream().flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
    }
}
package ru.job4j.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int leftSize = left.length();
        int rightSize = right.length();
        int result = leftSize - rightSize;
        int min = Math.min(leftSize, rightSize);
        int index = 0;
        while (index < min) {
            if (Character.compare(left.charAt(index), right.charAt(index)) != 0) {
                result = Character.compare(left.charAt(index), right.charAt(index));
            }
            index++;
        }
        return result;
    }
}
package ru.job4j.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        char[] leftChar = left.toCharArray();
        char[] rightChar = right.toCharArray();
        int minLength = Math.min(leftChar.length, rightChar.length);
        int result = 0;
        for (int i = 0; i < minLength; i++) {
            if (leftChar[i] != rightChar[i]) {
                result = Character.compare(leftChar[i], rightChar[i]);
                break;
            }
        }
        if (result == 0 && leftChar.length != rightChar.length) {
            result = Integer.compare(leftChar.length, rightChar.length);
        }
        return result;
    }
}
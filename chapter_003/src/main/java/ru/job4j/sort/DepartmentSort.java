package ru.job4j.sort;

import java.util.*;

public class DepartmentSort {
    /**
     *
     * @param source
     * @return list of elements in natural order
     */
    public ArrayList<String> sort(ArrayList<String> source) {
        check(source);
        Set<String> set = new TreeSet<>(source);
        return new ArrayList<>(set);
    }

    /**
     *
     * @param source
     * @return list of elements in descending order
     */
    public List<String> descSort(ArrayList<String> source) {
        check(source);
        source.sort((o1, o2) -> {
            int minLen = Math.min(o1.length(), o2.length());
            int result = -o1.substring(0, minLen).compareTo(o2.substring(0, minLen));
            return result != 0 ? result : Integer.compare(o1.length(), o2.length());
        });
        return source;
    }

    /**
     * checks for departments that been missed
     * @param list
     */
    private void check(ArrayList<String> list) {
        for (int out = 0; out < list.size(); out++) {
            String[] separated = list.get(out).split("\\\\");
            if (separated.length > 1) {
                String temp = separated[0];
                for (int in = 1; in < separated.length; in++) {
                    if (!list.contains(temp)) {
                        list.add(temp);
                    }
                    temp += "\\" + separated[in];
                }
            }
        }
    }
}

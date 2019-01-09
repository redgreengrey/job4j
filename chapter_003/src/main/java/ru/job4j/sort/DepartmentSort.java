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
        source.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return source;
    }

    /**
     *
     * @param source
     * @return list of elements in descending order
     */
    public List<String> descSort(ArrayList<String> source) {
        check(source);
        source.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result;
                if (o1.length() == o2.length()) {
                    result = o2.compareTo(o1);
                } else {
                    int size = Math.min(o1.length(), o2.length());
                    String first = o1.substring(0, size);
                    String second = o2.substring(0, size);
                    if (first.compareTo(second) == 0 && o1.length() > o2.length()) {
                        result = 1;
                    } else if (first.compareTo(second) == 0 && o1.length() < o2.length()) {
                        result = -1;
                    } else {
                        result = o2.compareTo(o1);
                    }
                }
                return result;
            }
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

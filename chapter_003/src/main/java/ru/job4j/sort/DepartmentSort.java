package ru.job4j.sort;

import java.util.*;

public class DepartmentSort {
    /**
     *
     * @param source
     * @return new treeSet in ascending order
     */
    public Set<String> sort(Set<String> source) {
        Set<String> result = new TreeSet<>();
        result.addAll(source);
        result.addAll(check(source));
        return result;
    }

    /**
     *
     * @param source
     * @return new treeSet in descending order
     */
    public Set<String> descSort(Set<String> source) {
        Set<String> result = new TreeSet<>(Comparator.reverseOrder());
        result.addAll(source);
        result.addAll(check(source));
        return result;
    }

    private Set<String> check(Set<String> set) {
        Set<String> result = new HashSet<>();
        for (String str : set) {
            for (int i = 0; i != str.length(); i++) {
                if (str.charAt(i) == '\\') {
                    String department = str.substring(0, i);
                    if (!set.contains(department)) {
                        result.add(department);
                    }
                }
            }
        }
        return result;
    }
}

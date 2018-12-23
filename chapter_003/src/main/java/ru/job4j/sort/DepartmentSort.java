package ru.job4j.sort;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class DepartmentSort {
    /**
     *
     * @param source
     * @return new treeSet in ascending order
     */
    public Set<String> sort(Set<String> source) {
        return new TreeSet<String>(source);
    }

    /**
     *
     * @param source
     * @return new treeSet in descending order
     */
    public Set<String> descSort(Set<String> source) {
        Set<String> result = new TreeSet<>(Comparator.reverseOrder());
        result.addAll(source);
        return result;
    }
}

package ru.job4j.user;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUser {
    public Set<User> sort(List<User> list) {
        Set<User> result = new TreeSet<>(list);
        result.addAll(list);
        return result;
    }
}

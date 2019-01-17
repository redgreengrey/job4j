package ru.job4j.user;

import java.util.*;

public class SortUser {
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    public List<User> sortNameLength(List<User> list) {
        Collections.sort(list,
                (o1, o2) -> {
                    Integer lengthO1 = o1.getName().length();
                    Integer lengthO2 = o2.getName().length();
                    return lengthO1.compareTo(lengthO2);
                });
        return list;
    }

    public List<User> sortByAllFields(List<User> list) {
        Collections.sort(list,
                (o1, o2) -> {
                    int byName = o1.getName().compareTo(o2.getName());
                    Integer ageO1 = o1.getAge();
                    Integer ageO2 = o2.getAge();
                    return byName == 0 ? ageO1.compareTo(ageO2) : byName;
                });
        return list;
    }
}

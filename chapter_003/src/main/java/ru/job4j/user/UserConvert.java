package ru.job4j.user;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserConvert {
    public Map<Integer, User> process(List<User> list) {
        return list.stream().collect(Collectors.toMap(User::getId, x -> x));
    }
}

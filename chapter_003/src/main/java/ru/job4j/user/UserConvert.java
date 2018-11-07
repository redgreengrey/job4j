package ru.job4j.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserConvert {
    public Map<Integer, User> process(List<User> list) {
        Map<Integer, User> map = new HashMap<>();
        for (User user : list) {
            map.put(user.getId(), user);
        }
        return map;
    }
}

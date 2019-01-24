package ru.job4j.user;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserConvertTest {
    @Test
    public void whenConvertListThenGetMap() {
        List<User> list = new ArrayList<>();
        Map<Integer, User> map;
        UserConvert converter = new UserConvert();
        User first = new User(1, "Jay", "New York");
        User second = new User(2, "Bob", "London");
        list.add(first);
        list.add(second);
        map = converter.process(list);
        Map<Integer, User> expect = Map.of(first.getId(), first, second.getId(), second);
        assertThat(map, is(expect));
    }
}

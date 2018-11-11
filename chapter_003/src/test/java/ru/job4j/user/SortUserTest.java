package ru.job4j.user;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void whenThen() {
        SortUser sortUser = new SortUser();
        User first = new User(1, "John", "London", 20);
        User second = new User(2, "Joe", "Washington", 30);
        List<User> usersList = new ArrayList<>();
        usersList.add(second);
        usersList.add(first);
        Set<User> result = sortUser.sort(usersList);
        Set<User> expect = new TreeSet<>();
        expect.add(second);
        expect.add(first);
        assertThat(result, is(expect));
    }
}
package ru.job4j.user;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void whenSortListThenGiveTreeMap() {
        SortUser sortUser = new SortUser();
        User first = new User(1, "John", "London", 20);
        User second = new User(2, "Joe", "Washington", 30);
        List<User> usersList = List.of(first, second);
        Set<User> result = sortUser.sort(usersList);
        Set<User> expected = Set.of(first, second);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSortNameByLengthThenGiveSortedList() {
        SortUser sortUser = new SortUser();
        User first = new User(1, "John", "Manchester", 25);
        User second = new User(2, "Steve", "Aberdeen", 32);
        List<User> list = new ArrayList<>();
        list.add(second);
        list.add(first);
        List<User> expect = List.of(first, second);
        List<User> result = sortUser.sortNameLength(list);
        assertThat(result.get(0).getName(), is(expect.get(0).getName()));
    }

    @Test
    public void whenSortByNameLengthThenGiveSortedList() {
        SortUser sort = new SortUser();
        List<User> users = new ArrayList<>();
        users.addAll(
                Arrays.asList(
                        new User(1, "John", "New York", 33),
                        new User(2, "Bob", "Austin", 55),
                        new User(3, "Steve", "Canberra", 22)
                )
        );
        sort.sortNameLength(users);
        assertThat(users.get(1).getName(), is("John"));
    }

    @Test
    public void whenSortAllFieldsThenGiveSortedList() {
        SortUser sortUser = new SortUser();
        List<User> list = new ArrayList<>(Arrays.asList(
                new User(0, "Billy", "Fargo", 44),
                new User(2, "Lester", "Fargo", 41),
                new User(1, "Gus", "Fargo", 32),
                new User(3, "Gus", "Fargo", 23)
        ));
        sortUser.sortByAllFields(list);
        assertThat(list.get(0).getAge(), is(44));
    }
}
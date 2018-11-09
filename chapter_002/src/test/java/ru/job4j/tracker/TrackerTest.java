package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.getAll().get(0), is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.update(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item[] items = {new Item("test1", "test1Description", 12L),
                new Item("test2", "test2Description", 123L)
        };
        List<Item> expect = Arrays.asList(items);
        Item third = new Item("test3", "test2Description", 1234L);
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(third);
        tracker.delete(third.getId());
        assertThat(expect, is(tracker.getAll()));
    }

    @Test
    public void whenAddItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.getAll().get(0), is(item));
    }


    @Test
    public void whenFindByIdThenReturnItem() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription1", 123L);
        tracker.add(first);
        Item expected = new Item("test1", "testDescription1", 123L);
        expected.setId(first.getId());
        assertThat(tracker.findById(first.getId()).getName(), is("test1"));
    }

    @Test
    public void whenFindByNameThenReturnItem() {
        Tracker tracker = new Tracker();
        Item first = new Item("item1", "item1Description", 12L);
        tracker.add(first);
        List<Item> expected = new ArrayList<>();
        expected.add(first);
        assertThat(tracker.findByName(first.getName()), is(expected));
    }

    @Test
    public void whenGetAllThenReturnAll() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription1", 12L);
        tracker.add(first);
        Item second = new Item("test2", "testDescription2", 123L);
        tracker.add(second);
        Item third = new Item("test3", "testDescription3", 1234L);
        tracker.add(third);
        List<Item> expected = new ArrayList<>();
        expected.add(first);
        expected.add(second);
        expected.add(third);
        assertThat(tracker.getAll(), is(expected));
    }
}

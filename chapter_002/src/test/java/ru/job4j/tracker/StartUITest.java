package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private Tracker tracker;

    @Before
    public void loadOutput() {
        tracker = new Tracker();
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUserUpdateItemThenTraackerHasUpdatedValue() {
        Item item = new Item("test name", "description");
        tracker.add(item);
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenUserDeleteItemThenTrackerHasNoSuchItem() {
        Item first = new Item("item name", "item description");
        Item second = new Item("item name 2", "item description 2");
        tracker.add(first);
        tracker.add(second);
        Input input = new StubInput(new String[]{"3", first.getId(), "1", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll()[0].getName(), is(second.getName()));
    }

    @Test
    public void whenUserFindItemByIdThenTrackerFindIt() {
        Item item = new Item("item", "desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"4", item.getId(), "1", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("item"));
    }

    @Test
    public void whenUserFindItemByNameThenTrackerFindIt() {
        Item item = new Item("item", "desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"5", item.getName(), "1", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName("item")[0], is(item));
    }
}

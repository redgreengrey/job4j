package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.getAll()[0].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    @Test
    public void whenUserUpdateItemThenTraackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "description");
        tracker.add(item);
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenUserDeleteItemThenTrackerHasNoSuchItem() {
        Tracker tracker = new Tracker();
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
        Tracker tracker = new Tracker();
        Item item = new Item("item", "desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"4", item.getId(), "1", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("item"));
    }

    @Test
    public void whenUserFindItemByNameThenTrackerFindIt() {
        Tracker tracker = new Tracker();
        Item item = new Item("item", "desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"5", item.getName(), "1", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName("item")[0], is(item));
    }
}

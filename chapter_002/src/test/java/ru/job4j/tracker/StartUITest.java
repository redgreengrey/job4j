package ru.job4j.tracker;

import com.sun.corba.se.spi.orbutil.fsm.InputImpl;
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
    private Tracker tracker = new Tracker();
    private final String lineSeparator = System.lineSeparator();
    private final StringBuilder menu = new StringBuilder()
            .append("------------------ Menu ------------------").append(System.lineSeparator())
            .append("0 : Add item").append(System.lineSeparator())
            .append("1 : Show all items").append(System.lineSeparator())
            .append("2 : Edit item").append(System.lineSeparator())
            .append("3 : Delete item").append(System.lineSeparator())
            .append("4 : Find item by Id").append(System.lineSeparator())
            .append("5 : Find items by name").append(System.lineSeparator())
            .append("6 : Exit Program").append(System.lineSeparator())
            .append("-----------------------------------------").append(System.lineSeparator());

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

    private void addTask(int num) {
        for (int i = 0; i < num; i++) {
            this.tracker.add(new Item("name" + i, "desc" + i));
        }
    }

    public void whenAddItemThenTrackerHasThatItem() {
        Input input = new StubInput(new String[]{});
    }

    @Test
    public void whenShowMenuThenTrackerShowMenu() {
        Input input = new StubInput(new String[]{"6", "y"});
        tracker = new Tracker();
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu)
                                .toString()
                )
        );
    }

    @Test
    public void whenThen() {

    }


    @Test
    public void whenShowMenuAndShowAllItemsThenTrackerShowOne() {
        Item first = new Item("name1", "desc1");
        tracker.add(first);
        Input input = new StubInput(new String[]{"1", "n", "6", "y"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("------------ List of items --------------")
                                .append(System.lineSeparator())
                                .append("Id: " + first.getId() + " Name: "
                                        + first.getName() + " Description: " + first.getDesc())
                                .append(System.lineSeparator())
                                .append("---------------------------------------")
                                .append(System.lineSeparator())
                                .append(menu)
                                .toString()
                )
        );
    }

    @Test
    public void whenFindByIdThenTrackerHasThatItem() {
        Item first = new Item("itemName", "itemDesc", 1L);
        tracker.add(first);
        Input input = new StubInput(new String[]{"4", first.getId(), "n", "6", "y"});
        new StartUI(input, tracker).init();
        String expect = new StringBuilder()
                .append(menu)
                .append("------------ Find item by id --------------").append(lineSeparator)
                .append("Id: " + first.getId() + " Name: "
                        + first.getName() + " Description: " + first.getDesc())
                .append(System.lineSeparator())
                .append("-----------------------------------------")
                .append(System.lineSeparator())
                .append(menu)
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
    }

    @Test
    public void whenfindItemByNameThanTrackerHasThatItem() {
        Item first = new Item("item", "description", 111L);
        tracker.add(first);
        Input input = new StubInput(new String[]{"5", first.getName(), "n", "6", "y"});
        new StartUI(input, tracker).init();
        String expect = new StringBuilder()
                .append(menu)
                .append("------------ Find item by name --------------").append(lineSeparator)
                .append("Id: " + first.getId()
                        + " Name: " + first.getName()
                        + " Description: " + first.getDesc()).append(lineSeparator)
                .append("-----------------------------------------")
                .append(System.lineSeparator())
                .append(menu)
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Input input = new StubInput(new String[]{"0", "test name", "desc", "n", "6", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll().get(0).getName(), is("test name"));
    }

    @Test
    public void whenUserUpdateItemThenTraackerHasUpdatedValue() {
        Item item = new Item("test name", "description");
        tracker.add(item);
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "item changed", "n", "6", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenUserDeleteItemThenTrackerHasNoSuchItem() {
        Item first = new Item("item name", "item description");
        Item second = new Item("item name 2", "item description 2");
        tracker.add(first);
        tracker.add(second);
        Input input = new StubInput(new String[]{"3", first.getId(), "n", "1", "n", "6", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll().get(0).getName(), is(second.getName()));
    }

    @Test
    public void whenUserFindItemByIdThenTrackerFindIt() {
        Item item = new Item("item", "desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"4", item.getId(), "n", "1", "n", "6", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("item"));
    }

    @Test
    public void whenUserFindItemByNameThenTrackerFindIt() {
        Item item = new Item("item", "desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"5", item.getName(), "n", "1", "n", "6", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName("item").get(0), is(item));
    }
}

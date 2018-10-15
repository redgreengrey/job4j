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
    private final String lineSeparator = System.lineSeparator();
    private final String menu = new StringBuilder()
            .append("Меню.").append(lineSeparator)
            .append("0. Добавить новую заявку").append(lineSeparator)
            .append("1. Показать все заявки").append(lineSeparator)
            .append("2. Изменить заявку").append(lineSeparator)
            .append("3. Удалить заявку").append(lineSeparator)
            .append("4. Найти заявку по id").append(lineSeparator)
            .append("5. Найти заявки по имени").append(lineSeparator)
            .append("6. Выйти из программы").append(lineSeparator)
            .append("Выберите действие: ").append(lineSeparator)
            .toString();

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
    public void whenShowMenuThenTrackerShowMenu() {
        Input input = new StubInput(new String[]{"6"});
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
    public void whenShowMenuAndShowAllItemsThenTrackerDoIt() {
        Item first = new Item("itemName", "desc", 123L);
        Item second = new Item("itemName2", "desc", 1234L);
        tracker.add(first);
        tracker.add(second);
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        String expect = new StringBuilder()
                .append(menu)
                .append("------------ Список заявок --------------").append(lineSeparator)
                .append(printItem(first)).append(lineSeparator)
                .append(printItem(second)).append(lineSeparator)
                .append("------------ Конец списка заявок --------------").append(lineSeparator)
                .append(menu)
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
    }

    private String printItem(Item item) {
        return new StringBuilder()
                .append("Имя заявки - " + item.getName()).append(lineSeparator)
                .append("Описание заявки - " + item.getDesc())
                .toString();
    }

    @Test
    public void whenFindByIdThenTrackerHasThatItem() {
        Item first = new Item("itemName", "itemDesc", 1L);
        tracker.add(first);
        Input input = new StubInput(new String[]{"4", first.getId(), "6"});
        new StartUI(input, tracker).init();
        String expect = new StringBuilder()
                .append(menu)
                .append("------------ Поиск заявки по id --------------").append(lineSeparator)
                .append("Искомая заявка - ").append("Item{"
                        + "id='" + first.getId() + '\''
                        + ", name='" + first.getName() + '\''
                        + ", description='" + first.getDesc() + '\''
                        + '}').append(lineSeparator)
                .append(menu)
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
    }

    @Test
    public void whenfindItemByNameThanTrackerHasThatItem() {
        Item first = new Item("item", "description", 111L);
        tracker.add(first);
        Input input = new StubInput(new String[]{"5", first.getName(), "6"});
        new StartUI(input, tracker).init();
        String expect = new StringBuilder()
                .append(menu)
                .append("------------ Поиск заявки по имени --------------").append(lineSeparator)
                .append("------------ Найдены следующие заявки ------------").append(lineSeparator)
                .append("Item{"
                        + "id='" + first.getId() + '\''
                        + ", name='" + first.getName() + '\''
                        + ", description='" + first.getDesc() + '\''
                        + '}').append(lineSeparator)
                .append(menu)
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
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

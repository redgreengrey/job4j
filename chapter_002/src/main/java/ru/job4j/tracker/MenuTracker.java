package ru.job4j.tracker;

/**
 * A class that implements the editing of an task by id.
 */
class EditItem extends BaseAction {
    public EditItem(int key, String name) {
        super(key, name);
    }

    public void execute(Input input, Tracker tracker) {
        boolean updated = false;
        System.out.println("------------ Изменение заявки --------------");
        String id = input.ask("Введите id заявки :");
        if (tracker.findById(id) != null) {
            updated = true;
            String name = input.ask("Введите имя новой заявки :");
            String desc = input.ask("Введите имя описание новой заявки :");
            Item item = new Item(name, desc);
            tracker.update(id, item);
        }
        if (updated) {
            System.out.println("------------ Заявка изменена --------------");
        } else {
            System.out.println("Заявок с id = " + id + " не найдено");
        }
        System.out.println("--------------------------------------------");
    }
}

/**
 * A class that implements the deleting of an task by id.
 */
class DeleteItem extends BaseAction {
    public DeleteItem(int key, String name) {
        super(key, name);
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Удаление заявки по id --------------");
        String id = input.ask("Введите id заявки:");
        boolean deleted = tracker.delete(id);
        if (deleted) {
            System.out.println("------------ Заявка удалена --------------");
        } else {
            System.out.println("Заявок с id = " + id + "не найдено");
        }
    }
}

/**
 * A class that implements the finding of an task by id.
 */
class FindItemById extends BaseAction {
    public FindItemById(int key, String name) {
        super(key, name);
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Найти заявку по id --------------");
        String id = input.ask("Введите id заявки :");
        Item found = tracker.findById(id);
        if (found != null) {
            System.out.println("Id: " + found.getId() + " Имя: "
                    + found.getName() + " Описание: " + found.getDesc());
        } else {
            System.out.println("Заявок с id = " + id + " не найдено");
        }
        System.out.println("-----------------------------------------");
    }
}

/**
 * A class that implements the finding of an task by name.
 */
class FindItemByName extends BaseAction {
    public FindItemByName(int key, String name) {
        super(key, name);
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Найти заявку по имени --------------");
        String name = input.ask("Введите имя заявки :");
        Item[] items = tracker.findByName(name);
        if (items.length > 0) {
            for (Item item : items) {
                System.out.println("Id: " + item.getId() + " Имя: "
                        + item.getName() + " Описание: " + item.getDesc());
            }
        } else {
            System.out.println("Заявок с именем " + name + " не нашлось");
        }
        System.out.println("-----------------------------------------");
    }
}

/**
 * MenuTracker class that implements menu actions.
 *
 * @version 0.1
 * @since 0.1
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[0] = this.new AddItem(0, "Add item");
        this.actions[1] = new GetAll(1, "Show all items");
        this.actions[2] = new EditItem(2, "Edit item");
        this.actions[3] = new DeleteItem(3, "Delete item");
        this.actions[4] = new FindItemById(4, "Find item by Id");
        this.actions[5] = new FindItemByName(5, "Find items by name");
        this.actions[6] = new Exit(6, "Exit Program");
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        System.out.println("------------------ Меню ------------------");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
        System.out.println("-----------------------------------------");
    }

    public int[] getRange() {
        int[] range = new int[this.actions.length];
        for (int i = 0; i < range.length; i++) {
            range[i] = i;
        }
        return range;
    }

    /**
     * The class that implements the addition of a new task.
     */
    private class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите имя заявки: ");
            String desc = input.ask("Введите описание заявки ");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------  Новая заявка с id: " + item.getId() + " -----------");
        }
    }

    /**
     * A class that implements getting of all tasks.
     */
    private static class GetAll extends BaseAction {
        public GetAll(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Список заявок --------------");
            for (Item items : tracker.getAll()) {
                System.out.println(
                        String.format("%s: %s %s: %s %s: %s", "Id", items.getId(),
                                "Имя", items.getName(), "Описание", items.getDesc()));
            }
            System.out.println("---------------------------------------");
        }
    }

    private static class Exit extends BaseAction {
        public Exit(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
        }
    }
}
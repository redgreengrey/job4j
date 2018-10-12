package ru.job4j.tracker;

/**
 * A class that implements the editing of an task by id.
 */
class EditItem implements UserAction {
    public int key() {
        return 2;
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Изменение заявки --------------");
        String id = input.ask("Введите id заявки :");
        String name = input.ask("Введите имя новой заявки :");
        String desc = input.ask("Введите имя описание новой заявки :");
        tracker.update(id, new Item(name, desc));
        System.out.println("--------------------------------------------");
    }

    public String info() {
        return String.format("%s. %s", this.key(), "Изменить заявку");
    }
}

/**
 * A class that implements the deleting of an task by id.
 */
class DeleteItem implements UserAction {
    public int key() {
        return 3;
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

    public String info() {
        return String.format("%s. %s", this.key(), "Удалить заявку");
    }
}

/**
 * A class that implements the finding of an task by id.
 */
class FindItemById implements UserAction {
    public int key() {
        return 4;
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Найти заявку по id --------------");
        String id = input.ask("Введите id заявки :");
        Item found = tracker.findById(id);
        if (tracker.findById(id) != null) {
            System.out.println("Id: " + found.getId() + " Имя: "
                    + found.getName() + " Описание: " + found.getDesc());
        } else {
            System.out.println("Заявок с id = " + id + " не найдено");
        }
        System.out.println("-----------------------------------------");
    }

    public String info() {
        return String.format("%s. %s", this.key(), "Find task by Id.");
    }
}

/**
 * A class that implements the finding of an task by name.
 */
class FindItemByName implements UserAction {
    public int key() {
        return 5;
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

    public String info() {
        return String.format("%s. %s", this.key(), "Найти заявки по имени");
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
    private UserAction[] actions = new UserAction[6];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = new GetAll();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindItemById();
        this.actions[5] = new FindItemByName();
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
        System.out.println("6. Выйти из программы.");
        System.out.println("-----------------------------------------");
    }

    /**
     * The class that implements the addition of a new task.
     */
    private class AddItem implements UserAction {
        public int key() {
            return 0;
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите имя заявки: ");
            String desc = input.ask("Введите описание заявки ");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------  Новая заявка с id: " + item.getId() + " -----------");
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Добавление новой заявки");
        }
    }

    /**
     * A class that implements getting of all tasks.
     */
    private static class GetAll implements UserAction {
        public int key() {
            return 1;
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

        public String info() {
            return String.format("%s. %s", this.key(), "Показать все заявки");
        }
    }
}
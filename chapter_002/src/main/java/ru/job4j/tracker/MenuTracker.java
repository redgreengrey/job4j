package ru.job4j.tracker;

import java.util.List;
import java.util.function.Consumer;

/**
 * A class that implements the editing of an task by id.
 */
class EditItem extends BaseAction {
    public EditItem(int key, String name) {
        super(key, name);
    }

    public void execute(Input input, Tracker tracker, Consumer<String> output) {
        boolean updated = false;
        System.out.println("------------ Update item --------------");
        String id = input.ask("Enter item's id");
        if (tracker.findById(id) != null) {
            updated = true;
            String name = input.ask("Enter new item name");
            String desc = input.ask("enter new item description");
            Item item = new Item(name, desc);
            tracker.update(id, item);
        }
        if (updated) {
            System.out.println("------------ Item updated --------------");
        } else {
            System.out.println("Item with id = " + id + " not found");
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

    public void execute(Input input, Tracker tracker, Consumer<String> output) {
        System.out.println("------------ Delete item by id --------------");
        String id = input.ask("Enter item's id");
        boolean deleted = tracker.delete(id);
        if (deleted) {
            System.out.println("------------ item deleted --------------");
        } else {
            System.out.println("Item with id = " + id + " not found");
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

    public void execute(Input input, Tracker tracker, Consumer<String> output) {
        System.out.println("------------ Find item by id --------------");
        String id = input.ask("Enter item's id");
        Item found = tracker.findById(id);
        if (found != null) {
            System.out.println("Id: " + found.getId() + " Name: "
                    + found.getName() + " Description: " + found.getDesc());
        } else {
            System.out.println("Items with id = " + id + " not found");
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

    public void execute(Input input, Tracker tracker, Consumer<String> output) {
        System.out.println("------------ Find item by name --------------");
        String name = input.ask("Enter item's name :");
        List<Item> items = tracker.findByName(name);
        if (((List) items).size() > 0) {
            for (Item item : items) {
                System.out.println("Id: " + item.getId() + " Name: "
                        + item.getName() + " Description: " + item.getDesc());
            }
        } else {
            System.out.println("Items with name " + name + " not found");
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
    private Consumer<String> output;
    private UserAction[] actions = new UserAction[7];


    public MenuTracker(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
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
        this.actions[key].execute(this.input, this.tracker, this.output);
    }

    public void show() {
        System.out.println("------------------ Menu ------------------");
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

        public void execute(Input input, Tracker tracker, Consumer<String> output) {
            String name = input.ask("Enter item's name: ");
            String desc = input.ask("Enter item's description");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------  New item with id: " + item.getId() + " -----------");
        }
    }

    /**
     * A class that implements getting of all tasks.
     */
    private static class GetAll extends BaseAction {
        public GetAll(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker, Consumer<String> output) {
            System.out.println("------------ List of items --------------");
            for (Item item : tracker.getAll()) {
                output.accept(String.format("Id: %s Name: %s Desc: %s",
                        item.getId(), item.getName(), item.getDesc()));
            }
            System.out.println("---------------------------------------");
        }
    }

    private static class Exit extends BaseAction {
        public Exit(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker, Consumer<String> output) {
        }
    }
}
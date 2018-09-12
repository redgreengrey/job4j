package ru.job4j.tracker;

import java.util.Random;

public class Tracker {
    private Item[] items = new Item[100];
    private int position = 0;
    private static final Random random = new Random();

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
        return item;
    }

    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + random.nextInt());
    }

    public void update(String id, Item item) {
        for (int i = 0; i != position; i++) {
            if (items[i].getId().equals(findById(id).getId())) {
                this.items[i] = item;
                break;
            }
        }
    }

    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    public Item[] findByName(String key) {
        Item[] result = null;
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            if (item != null && item.getName().equals(key)) {
                result[i] = item;
            }
        }
        return result;
    }


    public void delete(Item item) {
        for (int i = 0; i != position; i++) {
            if (items[i].equals(findById(item.getId()))) {
                this.items[i] = null;
                break;
            }
        }
    }

    public Item[] getAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index < this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }
}

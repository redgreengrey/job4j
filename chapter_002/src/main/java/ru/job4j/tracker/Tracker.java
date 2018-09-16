package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Tracker {
    private Item[] items = new Item[100];
    private int position = 0;
    private static final Random RANDOM = new Random();

    /**
     * Добавление заявки
     *
     * @param item
     */
    public void add(Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
    }

    /**
     * Создание произвольного значения для id
     *
     * @return String
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RANDOM.nextInt());
    }

    /**
     * Изменение заявки по id
     *
     * @param id
     * @param item
     */
    public void update(String id, Item item) {
        for (int i = 0; i != position; i++) {
            if (items[i].getId().equals(findById(id).getId())) {
                this.items[i] = item;
                this.items[i].setId(id);
                break;
            }
        }
    }

    /**
     * Получение заявки по id
     *
     * @param id id заявки
     * @return Item
     */
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

    /**
     * Нахождение заявки по имени
     *
     * @param key имя заявки
     * @return Item
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[position];
        int max = 0;
        for (int i = 0; i < position; i++) {
            if ((this.items[i] != null) && (this.items[i].getName().equals(key))) {
                result[max] = items[i];
                max++;
            }
        }
        return Arrays.copyOf(result, max);
    }


    /**
     * Удаление заявок
     *
     * @param id id заявки
     */
    public void delete(String id) {
        for (int i = 0; i < position; i++) {
            if ((this.items[i] != null) && (this.items[i].getId().equals(id))) {
                System.arraycopy(items, i + 1, items, i, items.length - i - 1);
                position--;
                break;
            }
        }
    }

    /**
     * Получение массива заявок
     *
     * @return Item[]
     */
    public Item[] getAll() {
        return Arrays.copyOf(this.items, this.position);
    }
}

package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tracker {
    private List<Item> items = new ArrayList<>();
//    private int position = 0;
    private static final Random RANDOM = new Random();

    /**
     * Добавление заявки
     *
     * @param item
     */
    public void add(Item item) {
        item.setId(generateId());
        this.items.add(item);
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
    public boolean update(String id, Item item) {
        boolean result = false;
        if (item != null) {
            result = true;
            for (int i = 0; i < this.items.size(); i++) {
                if (this.items.get(i).getId().equals(id)) {
                    item.setId(id);
                    items.set(i, item);
                }
            }
//            for (int i = 0; i != position; i++) {
//                if (items.get(i).getId().equals(findById(id).getId())) {
//                    this.items.set(i, item);
//                    this.items.get(i).setId(id);
//                    break;
//                }
//            }
        }
        return result;
    }

    /**
     * Получение заявки по id
     *
     * @param id id заявки
     * @return Item
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
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
    public List<Item> findByName(String key) {
        List<Item> itemsList = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getName().equals(key)) {
                itemsList.add(item);
            }
        }
//        Item[] result = new Item[position];
//        int max = 0;
//        for (int i = 0; i < position; i++) {
//            if ((this.items.get(i) != null) && (this.items.get(i).getName().equals(key))) {
//                result[max] = items.get(i);
//                max++;
//            }
//        }
//        return Arrays.copyOf(result, max);
        return itemsList;
    }


    /**
     * Удаление заявок
     *
     * @param id id заявки
     */
    public boolean delete(String id) {
        boolean result = false;
//        for (int i = 0; i < position; i++) {
//            if ((this.items.get(i) != null) && (this.items.get(i).getId().equals(id))) {
//                System.arraycopy(items, i + 1, items, i, items.length - i - 1);
//                position--;
//                result = true;
//                break;
//            }
//        }

        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId().equals(id)) {
                this.items.remove(i);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Получение массива заявок
     *
     * @return Item[]
     */
    public List<Item> getAll() {
        return this.items;
    }
}

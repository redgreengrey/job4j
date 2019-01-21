package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tracker {
    private List<Item> items = new ArrayList<>();
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
        List<Item> result = this.items.stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        return result.stream().findFirst().orElse(null);
    }

    /**
     * Нахождение заявки по имени
     *
     * @param key имя заявки
     * @return Item
     */
    public List<Item> findByName(String key) {
        List<Item> itemsList = new ArrayList<>(this.items);
        itemsList.stream().filter(x -> x.getName().equals(key)).collect(Collectors.toList());
        return itemsList;
    }


    /**
     * Удаление заявок
     *
     * @param id id заявки
     */
    public boolean delete(String id) {
        boolean result = false;
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

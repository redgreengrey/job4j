package ru.job4j.tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        int key;
        do {
            menu.show();
            key = Integer.valueOf(this.input.ask("Выберите номер пункта меню:"));
            if (key >= 0 && key < 6) {
                menu.select(key);
            } else if (key == 6) {
                System.out.println("Выход из программы.");
            } else {
                System.out.println("______________________________");
                System.out.println("Неверный номер. Поробуйте еще!");
            }
        } while (key != 6);
    }

    /**
     * Запускт программы.
     *
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
    }
}
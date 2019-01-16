package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    private Consumer<String> output;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input   ввод данных.
     * @param output
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Consumer<String> output, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker, output);
        menu.fillActions();
        do {
            menu.show();
            menu.select(input.ask("Select number of menu:", menu.getRange()));
        } while (!"y".equals(this.input.ask("Exit? (y): ")));
    }

    /**
     * Запускт программы.
     *
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), System.out::println, new Tracker()).init();
    }
}
package ru.job4j.tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    private static final String ADD = "0";
    private static final String SHOW = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FINDBYID = "4";
    private static final String FINDBYNAME = "5";
    private static final String EXIT = "6";

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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.showItem();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FINDBYID.equals(answer)) {
                this.findItemById();
            } else if (FINDBYNAME.equals(answer)) {
                this.findItemByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод отображающий основной цикл программы
     */
    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Добавить новую заявку");
        System.out.println("1. Показать все заявки");
        System.out.println("2. Изменить заявку");
        System.out.println("3. Удалить заявку");
        System.out.println("4. Найти заявку по id");
        System.out.println("5. Найти заявки по имени");
        System.out.println("6. Выйти из программы");
        System.out.println("Выберите действие: ");
    }

    /**
     * Метод реализует добавления новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Добавлена новая заявка с id : " + item.getId() + " -----------");
    }

    /**
     * Метод реализует отображение всех заявок
     */
    private void showItem() {
        System.out.println("------------ Список заявок --------------");
        for (Item item : this.tracker.getAll()) {
            System.out.println("Имя заявки - " + item.getName());
            System.out.println("Описание заявки - " + item.getDescription());
        }
        System.out.println("------------ Конец списка заявок --------------");
    }

    /**
     * Метод реализует поиск заявки по id
     */
    private void findItemById() {
        System.out.println("------------ Поиск заявки по id --------------");
        String idToFind = this.input.ask("------------ Введите id заявки --------------");
        System.out.println("Искомая заявка - " + this.tracker.findById(idToFind).getName());
    }

    /**
     * Метод реализует поиск заявок по имени
     */
    private void findItemByName() {
        System.out.println("------------ Поиск заявки по имени --------------");
        String name = this.input.ask("------------ Введите имя заявки --------------");
        System.out.println("------------ Найдены следующие заявки ------------");
        for (Item item : this.tracker.findByName(name)) {
            System.out.println(item.getName());
        }
    }

    /**
     * Метод реализует удаление выбранной заявки по id
     */
    private void deleteItem() {
        System.out.println("------------ Удаление элемента по ID ------------");
        String id = this.input.ask("Введите Id заявки : ");
        if (this.tracker.delete(id)) {
            System.out.println("Заявка удалена");
        } else {
            System.out.println("Заявка по id \"" + id + "\" не найдена");
        }
    }

    /**
     * Метод реализует изменение выбранной заявки
     */
    private void editItem() {
        System.out.println("------------ Изменение новой заявки --------------");
        String idOfUpdated = this.input.ask("------------ Введите id заявки, которую следует изменить --------------");
        if (this.tracker.findById(idOfUpdated) != null) {
            String nameOfNew = this.input.ask("------------ Введите имя новой заявки --------------");
            String descOfNew = this.input.ask("------------ Введите описание новой заявки --------------");
            Item item = new Item(nameOfNew, descOfNew);
            this.tracker.update(idOfUpdated, item);
            System.out.println("------------ Заявка изменена --------------");
        } else {
            System.out.println("------------ Заявка не найдена --------------");
//        this.tracker.update(idOfUpdated, item);
//        System.out.println("------------ Заявка изменена --------------");
        }
    }

    /**
     * Запускт программы.
     *
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}



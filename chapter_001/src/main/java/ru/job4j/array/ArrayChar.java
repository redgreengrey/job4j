package ru.job4j.array;

/**
 * Обертка над строкой.
 */
public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет. что слово начинается с префикса.
     *
     * @param prefix префикс.
     * @return если слово начинаеться с префикса
     */
    public boolean startWith(String prefix) {
        boolean result = false;
        char[] value = prefix.toCharArray();
        if (this.data[0] == value[0] && this.data[1] == value[1]) {      // проверить. что массив data имеет первые элементы одинаковые с value
            result = true;
        }
        return result;
    }
}
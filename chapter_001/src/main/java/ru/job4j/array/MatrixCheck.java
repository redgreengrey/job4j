package ru.job4j.array;

public class MatrixCheck {
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 0; i < data.length - 1; i++) {
            //       for (int j = 0; j < data.length - 1; j++) {
            if ((data[i][i] != data[i + 1][i + 1]) && (data[i][data.length - 1 - i] != data[i + 1][data.length - 1 - i])) {
                result = false;
            }
            //       }
        }
        return result;
    }

}

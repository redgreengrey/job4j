package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator {
    private final int[][] values;
    private int in = 0;
    private int out = 0;

    MatrixIterator(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return out < values.length && in < values[out].length;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = 0;
        if (in < values[out].length - 1) {
            result = this.values[out][in++];
        } else if (in == values[out].length - 1) {
            result = this.values[out++][in];
            in = 0;
        }
        return result;
    }
}

package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] values;
    private int index = 0;


    EvenNumbersIterator(int[] values) {
        this.values = values;
    }


    @Override
    public boolean hasNext() {
        boolean result = false;
        if (index < values.length) {
            result = IntStream.range(index, values.length).anyMatch(position -> values[position] % 2 == 0);
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (values[index] % 2 != 0) {
            index++;
        }
        return values[index++];
    }
}

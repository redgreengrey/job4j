package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            private Iterator<Integer> currentIterator;

            private void check() {
                if (currentIterator == null && it.hasNext()) {
                    currentIterator = it.next();
                }
            }

            @Override
            public boolean hasNext() {
                check();
                if (currentIterator == null) {
                    return false;
                }
                if (currentIterator.hasNext()) {
                    return true;
                }
                if (it.hasNext()) {
                    currentIterator = it.next();
                }
                return currentIterator.hasNext();
            }

            @Override
            public Integer next() {
                check();
                if (currentIterator == null) {
                    throw new NoSuchElementException();
                }

                if (!currentIterator.hasNext() && it.hasNext()) {
                    currentIterator = it.next();
                }
                return currentIterator.next();
            }
        };
    }
}
package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> currentIterator = it.next();

            @Override
            public boolean hasNext() {
                boolean res = false;
                if (currentIterator.hasNext()) {
                    res = true;
                } else {
                    while (it.hasNext()) {
                        currentIterator = it.next();
                        if (currentIterator.hasNext()) {
                            res = true;
                            break;
                        }
                    }
                }
                return res;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return currentIterator.next();
            }
        };
    }
}
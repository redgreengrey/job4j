package generic;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] objects;
    private int index = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];

    }

    void add(T value) {
        this.objects[index++] = value;
    }

    void set(int index, T value) {
        this.objects[index] = value;
    }

    void remove(int index) {
        System.arraycopy(this.objects, index + 1, this.objects, index, this.index - index + 1);
        this.index--;
    }

    T get(int index) {
        return (T) this.objects[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int position;

            @Override
            public boolean hasNext() {
                return position < objects.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) objects[position++];
            }
        };
    }
}

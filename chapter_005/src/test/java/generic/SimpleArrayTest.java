package generic;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {

    @Test
    public void whenAddElementInSimpleArray() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(2);
        simpleArray.add(1);
        simpleArray.add(10);
        Integer expect = 10;
        assertThat(simpleArray.get(1), is(expect));
    }

    @Test
    public void whenSetElementInSimpleArray() {
        SimpleArray<Long> simpleArray = new SimpleArray<>(1);
        simpleArray.add(100L);
        simpleArray.set(0, 9000L);
        Long expect = 9000L;
        assertThat(simpleArray.get(0), is(expect));
    }

    @Test
    public void whenRemoveInSimpleArray() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("yahoo");
        simpleArray.add("amazon");
        simpleArray.add("google");
        simpleArray.remove(1);
        assertThat(simpleArray.get(1), is("google"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateEmptySimpleArray() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(0);
        Iterator<Integer> iterator = simpleArray.iterator();
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterationGoesBad() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        Iterator<Integer> iterator = simpleArray.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }
}
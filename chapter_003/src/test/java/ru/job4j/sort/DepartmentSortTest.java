package ru.job4j.sort;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DepartmentSortTest {
    private DepartmentSort depSorter;
    private Set<String> directory;

    @Before
    public void setDirectory() {
        this.depSorter = new DepartmentSort();
        this.directory = new TreeSet<>();
        this.directory.add("K1\\SK1");
        this.directory.add("K2\\SK1\\SSK2");
        this.directory.add("K1\\SK2");
        this.directory.add("K2\\SK1\\SSK1");
        this.directory.add("K1\\SK1\\SSK1");
        this.directory.add("K1\\SK1\\SSK2");
        this.directory.add("K2\\SK1");
    }

    @Test
    public void whenSortAscending() {
        Set<String> set = depSorter.sort(this.directory);
        String[] result = set.toArray(new String[set.size()]);
        String[] expect = new String[9];
        expect[0] = "K1";
        expect[1] = "K1\\SK1";
        expect[2] = "K1\\SK1\\SSK1";
        expect[3] = "K1\\SK1\\SSK2";
        expect[4] = "K1\\SK2";
        expect[5] = "K2";
        expect[6] = "K2\\SK1";
        expect[7] = "K2\\SK1\\SSK1";
        expect[8] = "K2\\SK1\\SSK2";
        assertThat(result, is(expect));
    }

    @Test
    public void whenSortDeccending() {
        Set<String> set = depSorter.descSort(this.directory);
        String[] result = set.toArray(new String[set.size()]);
        String[] expect = new String[9];
        expect[0] = "K2\\SK1\\SSK2";
        expect[1] = "K2\\SK1\\SSK1";
        expect[2] = "K2\\SK1";
        expect[3] = "K2";
        expect[4] = "K1\\SK2";
        expect[5] = "K1\\SK1\\SSK2";
        expect[6] = "K1\\SK1\\SSK1";
        expect[7] = "K1\\SK1";
        expect[8] = "K1";
        assertThat(result, is(expect));
    }
}
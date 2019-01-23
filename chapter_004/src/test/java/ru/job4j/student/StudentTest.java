package ru.job4j.student;

import org.hamcrest.*;
import org.junit.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

public class StudentTest {
    @Test
    public void whenUseLevelOf() {
        Student student = new Student();
        List<Student> students = new ArrayList<>();
        students.add(new Student("Luke Skywalker", 65));
        students.add(new Student("Darth Vader", 85));
        students.add(new Student("Choobakka", 55));
        List<Student> result = student.levelOf(students, 60);
        List<Student> expect = new ArrayList<>();
        expect.add(new Student("Luke Skywalker", 65));
        expect.add(new Student("Darth Vader", 85));
        assertThat(result, is(expect));
    }
}

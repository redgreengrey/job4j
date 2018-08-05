package java.ru.job4j.calculator;

import org.junit.Test;
import ru.job4j.calculator.Calculator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenDiv2On2Then1() {
        Calculator calculator = new Calculator();
        calculator.add(2D, 2D);
        double result = calculator.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenSubtract2From2Then0() {
        Calculator calc = new Calculator();
        calc.add(1D, 2D);
        double result = calc.getResult();
        double expected = 0D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenMultiply2And2Then4() {
        Calculator calc = new Calculator();
        calc.add(2D, 2D);
        double result = calc.getResult();
        double expected = 4D;
        assertThat(result, is(expected));
    }

}
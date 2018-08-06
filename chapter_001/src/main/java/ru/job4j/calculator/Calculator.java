package ru.job4j.calculator;

public class Calculator {
    private double result;

    public void add(double first, double second) {
        this.result = first + second;
    }

    public void dev(double first, double second) {
        this.result = first / second;
    }

    public void multiply(double first, double second) {
        this.result = first * second;
    }

    public void subtract(double first, double second) {
        this.result = second - first;
    }

    public double getResult() {
        return this.result;
    }
}
//все ок! ну теперь да, а это изза переименования классов?
// хсорош:)
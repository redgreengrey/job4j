package ru.job4j.bank;

import java.util.Objects;

public class Account {
    private double value;
    private Integer requisites;

    public Account(double value, int requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getRequisites() {
        return requisites;
    }

    public void setRequisites(int requisites) {
        this.requisites = requisites;
    }

    /**
     * метод проврки возможности перевода денежных средств
     *
     * @param destination
     * @param amount
     * @return
     */
    public boolean transferAmount(Account destination, double amount) {
        boolean result = false;
        if (amount > 0 && amount < this.value) {
            this.value -= amount;
            destination.value += amount;
            result = true;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Double.compare(account.value, value) == 0
                && requisites == account.requisites;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, requisites);
    }

    @Override
    public String toString() {
        return String.format("Account [Requisites: %s. Value: %s.]", requisites, value);
    }
}

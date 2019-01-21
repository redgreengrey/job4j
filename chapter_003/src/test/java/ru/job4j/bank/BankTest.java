package ru.job4j.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {
    private String lineSeparator = System.lineSeparator();
    private User first = new User("John", "123456");
    private User second = new User("Jane", "654321");
    private User third = new User("John", "123456");
    private Bank bank = new Bank();

    @Test
    public void whenEqualsUsersThenTrue() {
        assertThat(first, is(third));
    }

    @Test
    public void whenAddUserThenBankHasHim() {
        bank.addUser(first);
        assertThat(bank.getBank().containsKey(first), is(true));
    }

    @Test
    public void whenAddNullUser() {
        User nullUser = null;
        bank.addUser(nullUser);
        assertThat(bank.getBank().containsKey(nullUser), is(true));
    }

    @Test
    public void whenRemoveUserThenBankHasNotHim() {
        bank.addUser(first);
        bank.addUser(second);
        bank.deleteUser(first);
        assertThat(bank.getBank().containsKey(first), is(false));
    }

    @Test
    public void whenAddAccountToUserThenHimHasIt() {
        bank.addUser(first);
        bank.addAccountToUser(this.first, new Account(1, 456456));
        bank.addAccountToUser(this.first, new Account(5, 555555));
        List<Account> result = bank.getUserAccounts(this.first);
        List<Account> expect = new ArrayList<>(Arrays.asList(
                new Account(1, 456456),
                new Account(5, 555555)
        ));
        assertThat(result.get(1).toString(), is(expect.get(1).toString()));
    }

    @Test
    public void whenRemoveAccountFromUserThenHimHasNotIt() {
        bank.addUser(first);
        bank.addAccountToUser(first, new Account(7000, 7777));
        bank.addAccountToUser(first, new Account(8000, 8888));
        bank.addAccountToUser(first, new Account(9000, 9999));
        boolean result = bank.deleteAccountFromUser(first, 9999);
        assertThat(result, is(true));
    }

    @Test
    public void whenFindUserAccountsThenItFound() {
        bank.addUser(first);
        bank.addAccountToUser(first, new Account(50000, 5555));
        bank.addAccountToUser(first, new Account(60000, 6666));
        List<Account> temp = bank.getUserAccounts(first);
        List<Account> temp2 = new ArrayList<>(Arrays.asList(
                new Account(50000, 5555),
                new Account(60000, 6666)
        ));
        StringBuilder result = new StringBuilder();
        result.append(temp.get(0).toString());
        result.append(this.lineSeparator);
        result.append(temp.get(1).toString());
        StringBuilder expect = new StringBuilder();
        expect.append(temp2.get(0).toString());
        expect.append(this.lineSeparator);
        expect.append(temp2.get(1).toString());
        assertThat(result.toString(), is(expect.toString()));
    }

    @Test
    public void whenTransferMoneyThenTrue() {
        bank.addUser(first);
        bank.addUser(second);
        bank.addAccountToUser(first, new Account(50000, 555555));
//        bank.addAccountToUser(first, new Account(50000, 555555));
        bank.addAccountToUser(second, new Account(50000, 666666));
        double amount = 10000;
        boolean result = bank.transferMoney(first.getPassport(), 555555,
                                            second.getPassport(), 666666,
                                            amount);
        assertThat(result, is(true));
    }
}

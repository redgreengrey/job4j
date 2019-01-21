package ru.job4j.bank;

import java.util.*;

public class Bank {
    private Map<User, List<Account>> bank = new HashMap<>();

    Map<User, List<Account>> getBank() {
        return bank;
    }

    /**
     * метод добавления пользователя
     *
     * @param user - пользователь
     */
    public void addUser(User user) {
        this.bank.putIfAbsent(user, new ArrayList<>());
    }


    /**
     * метод удаления пользователя
     *
     * @param user - пользователь
     */
    void deleteUser(User user) {
        this.bank.remove(user);
    }

    /**
     * метод добавления банковского счета для пользователя
     *
     * @param user    user
     * @param account account to add
     */
    void addAccountToUser(User user, Account account) {
        if (bank.keySet().contains(user)) {
            this.bank.get(user).add(account);
        }
    }

    /**
     * метод получения списка пользовательских счетов
     *
     * @param user user
     * @return list of users accounts
     */
    List<Account> getUserAccounts(User user) {
        return this.bank.get(user);
    }

    /**
     * метод удаления аккаунта пользователя по реквизитам
     *
     * @param user       user
     * @param requisites requisites of user's account
     * @return boolean
     */
    boolean deleteAccountFromUser(User user, Integer requisites) {
        List<Account> list = this.bank.get(user);
        return list.removeIf(x -> x.getRequisites() == requisites);
    }

    /**
     * метод нахождения пользователя по паспорту
     *
     * @param passport passport of user
     * @return user
     */
    private User findUserByPassport(String passport) {
        Optional<User> user = bank.keySet().stream().filter(x -> x.getPassport().equals(passport)).findAny();
        return user.orElseGet(() -> user.orElse(null));
    }

    /**
     * метод нахождения счета по реквизитам
     *
     * @param passport  passport of given user
     * @param requisite requisites of account
     * @return account of user
     */
    private Account getUserAccountByRequisites(String passport, Integer requisite) {
        List<Account> listAccounts = this.getUserAccounts(this.findUserByPassport(passport));
        return listAccounts.stream().filter(x -> x.getRequisites() == requisite).findFirst().orElse(null);
    }

    /**
     * метод перевода средств
     *
     * @param srcPassport  source passport
     * @param srcRequisite source requisite
     * @param dstPassport  destination passport
     * @param dstRequisite destination requisite
     * @param amount       amount of transferred money
     * @return boolean
     */
    boolean transferMoney(String srcPassport, Integer srcRequisite,
                          String dstPassport, Integer dstRequisite, double amount) {
        boolean result = false;
        Account firstAccount = this.getUserAccountByRequisites(srcPassport, srcRequisite);
        Account secondAccount = this.getUserAccountByRequisites(dstPassport, dstRequisite);
        if (firstAccount != null && secondAccount != null) {
            result = firstAccount.transferAmount(secondAccount, amount);
        }
        return result;
    }
}

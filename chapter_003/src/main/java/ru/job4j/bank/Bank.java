package ru.job4j.bank;

import java.util.*;

public class Bank {
    private Map<User, List<Account>> bank = new HashMap<>();

    public Map<User, List<Account>> getBank() {
        return bank;
    }

//    public User getUser(String passport) {
//        User searched = new User();
//        boolean valid = false;
//        Set<User> keys = this.bank.keySet();
//        for (User user : keys) {
//            if (user.getPassport().equals(passport)) {
//                searched = user;
//                valid = true;
//                break;
//            }
//        }
//        return searched;
//    }

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
    public void deleteUser(User user) {
        this.bank.remove(user);
    }

    /**
     * метод добавления банковского счета для пользователя
     *
     * @param user
     * @param account
     */
    public void addAccountToUser(User user, Account account) {
        this.bank.get(user).add(account);
        this.bank.isEmpty();
    }

    /**
     * метод получения списка пользовательских счетов
     *
     * @param user
     * @return
     */
    public List<Account> getUserAccounts(User user) {
        return this.bank.get(user);
    }

    /**
     * метод удаления аккаунта пользователя по реквизитам
     *
     * @param user
     * @param requisites
     * @return
     */
    public boolean deleteAccountFromUser(User user, Integer requisites) {
        List<Account> list = this.bank.get(user);
        int index;
        boolean rst = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRequisites() == requisites) {
                index = i;
                this.bank.get(user).remove(index);
                rst = true;
                break;
            }
        }
        return rst;
    }

    /**
     * метод нахождения пользователя по пасспорту
     *
     * @param passport
     * @return
     */
    private User findUserByName(String passport) {
        User user = null;
        for (Map.Entry<User, List<Account>> entry : this.bank.entrySet()) {
            if (passport.equals(entry.getKey().getPassport())) {
                user = entry.getKey();
                break;
            }
        }
        return user;
    }

    /**
     * метод нахождения счета по реквизитам
     *
     * @param passport
     * @param requisite
     * @return
     */
    private Account getUserAccountByRequisites(String passport, Integer requisite) {
        Account account = null;
        List<Account> listAccounts = this.getUserAccounts(this.findUserByName(passport));
        for (int i = 0; listAccounts != null && i < listAccounts.size(); i++) {
            if (requisite.equals(listAccounts.get(i).getRequisites())) {
                account = listAccounts.get(i);
                break;
            }
        }
        return account;
    }

    /**
     * метод перевода средств
     *
     * @param srcPassport
     * @param srcRequisite
     * @param dstPassport
     * @param dstRequisite
     * @param amount
     * @return
     */
    public boolean transferMoney(String srcPassport, Integer srcRequisite,
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

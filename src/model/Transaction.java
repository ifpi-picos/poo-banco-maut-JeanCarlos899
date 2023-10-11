package model;

import java.util.Date;

public class Transaction {
    private Date date;
    private String type;
    private double value;
    private Account userAccount;

    public Transaction(Account account) {
        this.userAccount = account;
        this.date = new Date();
    }

    public boolean transfer(Account destinationAccount, double value) {
        if (this.userAccount.getBalance() >= value) {
            this.userAccount.withdraw(value);
            destinationAccount.deposit(value);
            this.value = value;
            return true;
        }
        this.type = "Transferência";

        return false;
    }

    public boolean deposit(Account account, double value) {
        if (value > 0) {
            account.deposit(value);
            this.value = value;
            return true;
        }
        this.type = "Depósito";

        return false;
    }

    public boolean withdraw(Account account, double value) {
        if (account.getBalance() >= value) {
            account.withdraw(value);
            this.value = value;
            return true;
        }
        this.type = "Saque";

        return false;
    }

    @Override
    public String toString() {
        return "Transação [data=" + date.toString() + ", tipo=" + type + ", valor=" + value + "]";
    }
}

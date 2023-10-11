package model;

import java.text.SimpleDateFormat;
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
            this.type = "Transferência";
            this.value = value;
            return true;
        }
        return false;
    }

    public boolean deposit(Account account, double value) {
        if (value > 0) {
            account.deposit(value);
            this.value = value;
            this.type = "Depósito";
            return true;
        }
        return false;
    }

    public boolean withdraw(Account account, double value) {
        if (account.getBalance() >= value) {
            account.withdraw(value);
            this.value = value;
            this.type = "Saque";
            return true;
        }

        return false;
    }

    public Account getUserAccount() {
        return userAccount;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        return "* %s feito em %s no valor de R$ %.2f".formatted(this.type, dateFormat.format(this.date), this.value);
    }
}

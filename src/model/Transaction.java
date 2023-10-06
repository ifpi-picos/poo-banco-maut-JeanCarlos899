package model;
import java.util.Date;
import java.util.UUID;

public class Transaction {
    private UUID accountUid;
    private Date date;
    private String type;
    private double value;

    public Transaction(UUID accountUid, double value) {
        this.accountUid = accountUid;
        this.value = value;
        this.date = new Date();
    }

    public boolean transfer(Account account, double value) {
        if (account.getBalance() >= value) {
            account.withdraw(value);
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

    public UUID getAccountUid() {
        return accountUid;
    }

    public String getDate() {
        return date.toString();
    }

    public String getType() {
        return type;
    }

    public double getValue() {
        return value;
    }
}

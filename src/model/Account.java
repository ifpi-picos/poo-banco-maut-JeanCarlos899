package model;

import java.util.UUID;

public class Account {
    private UUID accountUid;
    private UUID userUid;
    private String agency;
    private String number;
    private double balance;

    private static int nextAccountNumber = 1;

    public Account(UUID userUid, String agency) {
        this.accountUid = UUID.randomUUID();
        this.userUid = userUid;
        this.agency = agency;
        this.number = generateAccountNumber();
        this.balance = 0;
    }
    
    private String generateAccountNumber() {
        String accountNumber = String.format("%04d", nextAccountNumber);
        nextAccountNumber++;

        return accountNumber;
    }

    public void deposit(double value) {
        this.balance += value;
    }

    public boolean withdraw(double value) {
        if (value > this.balance) {
            return false;
        }
        this.balance -= value;

        return true;
    }
    
    public String getAgency() {
        return agency;
    }

    public String getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public UUID getUid() {
        return userUid;
    }
}

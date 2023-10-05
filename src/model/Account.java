package model;

import java.util.UUID;

public class Account {
    private UUID userUid;
    private String agency;
    private String number;
    private double balance;

    private static int nextAccountNumber = 1;

    public Account(UUID userUid, String agency, String number, double balance) {
        this.userUid = userUid;
        this.agency = agency;
        this.number = generateAccountNumber();
        this.balance = balance;
    }
    
    private String generateAccountNumber() {
        String accountNumber = String.format("%04d", nextAccountNumber);
        nextAccountNumber++;

        return accountNumber;
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

package br.edu.ifpi.poo.entities;

public abstract class Account {
    final private String agency;
    final private String accountNumber;
    private Client client;
    protected double balance = 0;

    private static int numberOfAccounts = 1; 

    public Account(String agency) {
        this.agency = agency;
        this.accountNumber = generateAccountNumber(); 
    }

    private String generateAccountNumber() {
        String accountNumber = String.format("%04d", numberOfAccounts);
        numberOfAccounts++;

        return accountNumber;
    }

    public abstract void transfer(Account destinationAccount, double value);

    public abstract void deposit(double value);

    public abstract void withdraw(double value);

    public String getAgency() {
        return agency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Client getClient() {
        return client;
    }

    public double getBalance() {
        return balance;
    }
}
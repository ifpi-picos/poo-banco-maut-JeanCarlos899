package br.edu.ifpi.poo.entities;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.poo.notifications.Notifications;

public abstract class Account {
    protected List<Transaction> transactions = new ArrayList<>();

    protected final String agency;
    protected final String accountNumber;
    protected Notifications notifications;
    protected double balance = 0;
    private Client client;

    // variável estática para gerar o número da conta
    private static int numberOfAccounts = 1;

    public Account(String agency, Client client, Notifications notifications) {
        this.agency = agency;
        this.accountNumber = generateAccountNumber();
        this.client = client;
        this.notifications = notifications;
    }

    public abstract void transfer(Account destinationAccount, double value);

    public abstract void deposit(double value, boolean notification);

    public abstract void withdraw(double value, boolean notification);

    public List<Transaction> getTransactions() {
        return transactions;
    }
    
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    
    public Notifications getNotifications() {
        return notifications;
    }

    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }
    
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
    
    private String generateAccountNumber() {
        String accountNumber = String.format("%04d", numberOfAccounts);
        numberOfAccounts++;

        return accountNumber;
    }
}
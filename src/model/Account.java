package model;

public class Account {
    private final String agency;
    private final String number;
    private final User user;
    private double balance;

    private static int nextAccountNumber = 1;

    public Account(String agency, User user) {
        this.agency = agency;
        this.user = user;
        this.number = generateAccountNumber();
        this.balance = 0;
    }

    private String generateAccountNumber() {
        String accountNumber = String.format("%04d", nextAccountNumber);
        nextAccountNumber++;

        return accountNumber;
    }

    public User getUser() {
        return user;
    }

    public Boolean deposit(double value) {
        if (value > 0) {
            this.balance += value;
            return true;
        }
        return false;
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

    @Override
    public String toString() {
        return """
                Agência = %s
                Número  = %s
                Usuário = %s
                Saldo   = %s""".formatted(agency, number, user.getName(), balance);
    }
}

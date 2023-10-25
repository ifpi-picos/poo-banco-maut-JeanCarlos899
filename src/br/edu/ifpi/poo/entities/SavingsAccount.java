package br.edu.ifpi.poo.entities;

import br.edu.ifpi.poo.notifications.Notifications;

public class SavingsAccount extends Account {
    public SavingsAccount(String agency, Client client, Notifications notifications) {
        super(agency, client, notifications);
    }

    @Override
    public void deposit(double value, boolean notification, boolean createTransaction) {
        if (value > 0) {
            // rendimento de 10% por depósito
            double depositValue = value * 1.1;
            super.balance += depositValue;
        }

        if (notification) {
            super.notifications.sendNotification("depósito", value);
        }

        if (createTransaction) {
            Transaction transaction = new Transaction("depósito", value);
            super.addTransaction(transaction);
        }
    }

    @Override
    public void withdraw(double value, boolean notification, boolean createTransaction) {
        if (value > 0 && value <= super.balance) {
            // taxa de 5% por saque
            double withdrawValue = value * 1.05;
            if (withdrawValue > super.balance) {
                return;
            }
            super.balance -= withdrawValue;
        }
        if (notification) {
            super.notifications.sendNotification("saque", value);
        }

        if (createTransaction) {
            Transaction transaction = new Transaction("saque", value);
            super.addTransaction(transaction);
        }
    }

    @Override
    public void transfer(Account destinationAccount, double value) {
        if (value > 0 && value <= super.balance) {
            // taxa de 10% por transferência
            double transferValue = value * 1.1;
            super.balance -= transferValue;
            destinationAccount.deposit(value, false, false);
        }

        super.notifications.sendNotification("transferência", value);

        Transaction transaction = new Transaction("transferência", value);
        super.addTransaction(transaction);
    }

    @Override
    public String toString() {
        return """
                Tipo: Conta Poupança
                Agência: %s
                Número da conta: %s
                Saldo: %.2f
                """.formatted(super.agency, super.accountNumber, super.balance);
    }
}

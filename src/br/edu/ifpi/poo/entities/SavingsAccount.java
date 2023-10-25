package br.edu.ifpi.poo.entities;

import br.edu.ifpi.poo.notifications.Notifications;

public class SavingsAccount extends Account {
    public SavingsAccount(String agency, Client client, Notifications notifications) {
        super(agency, client, notifications);
    }

    @Override
    public void deposit(double value) {
        if (value > 0) {
            // rendimento de 10% por depósito
            double depositValue = value * 1.1;
            super.balance += depositValue;
        }
    }

    @Override
    public void withdraw(double value) {
        if (value > 0 && value <= super.balance) {
            // taxa de 5% por saque
            double withdrawValue = value * 1.05;
            if (withdrawValue > super.balance) {
                return;
            }
            super.balance -= withdrawValue;
        }
    }

    @Override
    public void transfer(Account destinationAccount, double value) {
        if (value > 0 && value <= super.balance) {
            // taxa de 10% por transferência
            double transferValue = value * 1.1;
            withdraw(transferValue);
            destinationAccount.deposit(value);
        }
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

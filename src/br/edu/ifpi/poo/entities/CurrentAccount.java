package br.edu.ifpi.poo.entities;

import br.edu.ifpi.poo.notifications.Notifications;

public class CurrentAccount extends Account {
    private double overdraft;
    private int numberOfTransfers = 0;

    public CurrentAccount(String agency, Client client, Notifications notifications) {
        super(agency, client, notifications);
    }

    @Override
    public void transfer(Account destinationAccount, double value) {
        if (numberOfTransfers < 2) {
            this.withdraw(value, false, false);
            destinationAccount.deposit(value, false, true);
            numberOfTransfers++;
        } else {
            // taxa de 10% por transferência
            double transferValue = value * 1.1;
            this.withdraw(transferValue, false, false);
            destinationAccount.deposit(value, false, true);
        }
        super.notifications.sendNotification("transferência", value);

        Transaction transaction = new Transaction("transferência", value);
        super.addTransaction(transaction);
    }

    @Override
    public void deposit(double value, boolean notification, boolean createTransaction) {
        if (value > 0) {
            if (overdraft > 0) {
                // Se houver um valor positivo no cheque especial, parte do depósito é usada
                // para reduzir a dívida no cheque especial, e o restante é adicionado ao saldo
                if (value >= overdraft) {
                    // O depósito cobre totalmente o cheque especial
                    value -= overdraft;
                    overdraft = 0;
                    super.balance += value;
                } else {
                    // O depósito cobre parcialmente o cheque especial
                    overdraft -= value;
                }
            } else {
                // Se o cheque especial estiver zerado ou negativo, todo o valor do depósito
                // é adicionado ao saldo
                super.balance += value;
            }
        }
        // Evita notificação duplicada na transferência
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
        // verifica se o valor do saque é maior que o saldose for,
        // calcula o valor que falta para o saque zerar o saldo e
        // adiciona esse valor ao cheque especial
        if (super.balance < value) {
            double difference = super.balance - value;
            super.balance = 0;
            overdraft -= difference;
        } else {
            // se o valor for maior que o saldo, apenas subtrai o
            // valor não é necessário utilizar o cheque especial

            super.balance -= value;
        }

        if (notification) {
            super.notifications.sendNotification("saque", value);
        }

        if (createTransaction) {
            Transaction transaction = new Transaction("saque", value);
            super.addTransaction(transaction);
        }
    }

    public double getOverdraft() {
        return overdraft;
    }

    @Override
    public String toString() {
        return """
                Tipo: Conta Corrente
                Agência: %s
                Número da conta: %s
                Saldo: %.2f
                Cheque especial usado: %.2f
                """.formatted(super.agency, super.accountNumber, super.balance, overdraft);
    }
}

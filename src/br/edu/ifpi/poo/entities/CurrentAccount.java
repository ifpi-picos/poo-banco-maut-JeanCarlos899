package br.edu.ifpi.poo.entities;

public class CurrentAccount extends Account {
    private double overdraft;
    private int numberOfTransfers = 0;

    public CurrentAccount(String agency) {
        super(agency);
    }

    @Override
    public void transfer(Account destinationAccount, double value) {
        if (numberOfTransfers < 2) {
            // transferência sem taxa
            withdraw(value);
            destinationAccount.deposit(value);
            numberOfTransfers++;
        } else {
            // taxa de 10% por transferência
            double transferValue = value * 1.1;
            withdraw(transferValue);
            destinationAccount.deposit(value);
        }
    }

    @Override
    public void deposit(double value) {
        if (value > 0) {
            if (overdraft < 0 && (value + overdraft) < 0) {
                // se o cheque especial estiver negativo e o valor do depósito
                // for menor que o cheque especial, apenas adiciona o valor
                // ao cheque especial
                overdraft += value;
            } else if (overdraft < 0 && (value + overdraft) > 0) {
                // se o cheque especial estiver negativo e o valor do depósito
                // for maior que o cheque especial, adiciona o valor que falta
                // para zerar o cheque especial e adiciona o restante ao saldo
                double difference = overdraft + value;
                overdraft = 0;
                super.balance += difference;
            } else {
                // se o cheque especial estiver zerado ou positivo, apenas
                // adiciona o valor ao saldo
                super.balance += value;
            }
        }
    }

    @Override
    public void withdraw(double value) {
        // verifica se o valor do saque é maior que o saldo
        // se for, calcula o valor que falta para o saque
        // zerar o saldo e adiciona esse valor ao cheque especial
        if (super.balance < value) {
            double difference = super.balance - value;
            super.balance = 0;
            overdraft -= difference;
        } else {
            // se o valor for maior que o saldo, apenas subtrai o valor
            // não é necessário utilizar o cheque especial
            super.balance -= value;
        }
    }

    public double getOverdraft() {
        return overdraft;
    }
}

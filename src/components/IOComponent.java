package components;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.Account;
import model.Address;
import model.Transaction;
import model.User;

public class IOComponent {
  public static User createUser(Scanner scanner) {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date date = null;
    scanner.nextLine(); // Consume newline left-over

    InterfaceComponent.divider();
    System.out.println("Dados do usuário");
    InterfaceComponent.divider();
    System.out.println("Digite seu nome: ");
    String username = scanner.nextLine();
    System.out.println("Digite seu CPF: ");
    String cpf = scanner.nextLine();
    System.out.println("Digite sua senha: ");
    String password = scanner.nextLine();

    while (true) {
      System.out.println("Digite sua data de nascimento: ");
      String birthDate = scanner.nextLine();
      try {
        date = format.parse(birthDate);
        break;
      } catch (Exception e) {
        System.out.println("Data inválida");
      }
    }

    InterfaceComponent.divider();
    System.out.println("Endereço");
    InterfaceComponent.divider();
    System.out.println("Digite sua rua: ");
    String street = scanner.nextLine();
    System.out.println("Digite seu número: ");
    String number = scanner.nextLine();
    System.out.println("Digite sua cidade: ");
    String city = scanner.nextLine();
    System.out.println("Digite seu estado: ");
    String state = scanner.nextLine();
    System.out.println("Digite seu complemento: ");
    String complement = scanner.nextLine();

    Address address = new Address(street, number, city, state, complement);

    return new User(username, cpf, date, address, password);
  }

  public static String[] accessUser(Scanner scanner) {
    scanner.nextLine(); // Consume newline left-over

    InterfaceComponent.divider();
    System.out.println("Acesso");
    InterfaceComponent.divider();
    System.out.println("Digite seu CPF: ");
    String cpf = scanner.nextLine();

    System.out.println("Digite sua senha: ");
    String password = scanner.nextLine();

    return new String[] { cpf, password };
  }

  public static Account createAccount(Scanner scanner, User currentUser) {
    scanner.nextLine(); // Consume newline left-over

    InterfaceComponent.divider();
    System.out.println("Dados da conta");
    InterfaceComponent.divider();
    System.out.println("Digite a agência: ");
    String agency = scanner.nextLine();

    return new Account(currentUser.getUid(), agency);
  }

  public static Account accessAccount(Scanner scanner, List<Account> accounts, User currentUser) {
    scanner.nextLine(); // Consume newline left-over

    InterfaceComponent.divider();
    System.out.println("Acesso");
    InterfaceComponent.divider();
    System.out.println("Digite a agência: ");
    String agency = scanner.nextLine();
    System.out.println("Digite o número da conta: ");
    String number = scanner.nextLine();

    for (Account account : accounts) {
      if (account.getAgency().equals(agency) && account.getNumber().equals(number)) {
        return account;
      }
    }

    return null;
  }

  public static Transaction deposit(Scanner scanner, Account account) {
    scanner.nextLine(); // Consume newline left-over

    InterfaceComponent.divider();
    System.out.println("Depósito");
    InterfaceComponent.divider();
    System.out.println("Digite o valor: ");
    double value = scanner.nextDouble();

    Transaction transaction = new Transaction(account.getUid(), value);
    transaction.deposit(account, value);

    return transaction;
  }

  public static Transaction withdraw(Scanner scanner, Account account) {
    scanner.nextLine(); // Consume newline left-over

    InterfaceComponent.divider();
    System.out.println("Saque");
    InterfaceComponent.divider();
    System.out.println("Digite o valor: ");
    double value = scanner.nextDouble();

    Transaction transaction = new Transaction(account.getUid(), value);
    transaction.withdraw(account, value);

    return transaction;
  }

  public static Transaction transfer(Scanner scanner, Account account, List<Account> accounts) {
    scanner.nextLine(); // Consume newline left-over

    InterfaceComponent.divider();
    System.out.println("Transferência");
    InterfaceComponent.divider();
    System.out.println("Digite a agência: ");
    String agency = scanner.nextLine();
    System.out.println("Digite o número da conta: ");
    String number = scanner.nextLine();

    Account accountTransfer = null;

    for (Account a : accounts) {
      if (a.getAgency().equals(agency) && a.getNumber().equals(number)) {
        accountTransfer = a;
        break;
      }
    }

    if (accountTransfer == null) {
      System.out.println("Conta não encontrada");
      return null;
    }

    System.out.println("Digite o valor: ");
    double value = scanner.nextDouble();

    Transaction transaction = new Transaction(account.getUid(), value);
    transaction.transfer(accountTransfer, value);

    return transaction;
  }

  public static void extract(Account account, List<Transaction> transactions) {
    for (Transaction t : transactions) {
      if (t.getAccountUid().equals(account.getUid())) {
        System.out.println(t.getType() + " - " + t.getValue() + " - " + t.getDate());
      }
    }
  }
}

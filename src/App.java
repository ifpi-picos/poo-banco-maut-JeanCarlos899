import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.Account;
import model.Address;
import model.Transaction;
import model.User;

// Atividade de POO, banco Maut - Jean Carlos Rodrigues Sousa
// Análise e Desenvolvimento de Sistemas - 2023.2 - IFPI CAPIC
// Professor: Jesiel Viana

public class App {
  private static Scanner scanner = new Scanner(System.in);
  private static List<User> users = new ArrayList<>();
  private static List<Transaction> transactions = new ArrayList<>();
  private static List<Account> accounts = new ArrayList<>();
  private static Account currentAccount = null;

  public static void main(String[] args) {

    accounts.add(
        new Account("01", new User("Jean", "12345678910", new Date(), new Address("Rua 1", "1", "Teresina", "PI"))));
    accounts.add(new Account("01",
        new User("Karielly", "12345678911", new Date(), new Address("Rua 2", "2", "Teresina", "PI"))));

    while (true) {
      while (currentAccount == null) {
        showWelcomeMenu();
        int option = scanner.nextInt();
  
        switch (option) {
          case 0:
            exit();
            break;
          case 1:
            createAccount();
            break;
          case 2:
            accessAccount();
            break;
          default:
            System.out.println("Opção inválida");
        }
      }
  
      // Menu do usuário
      while (currentAccount != null) {
        showUserMenu();
        int option = scanner.nextInt();
  
        switch (option) {
          case 0:
            currentAccount = null;
            break;
          case 1:
            displayAccountInfo();
            break;
          case 2:
            displayUserInfo();
            break;
          case 3:
            displayBalance();
            break;
          case 4:
            deposit();
            break;
          case 5:
            withdraw();
            break;
          case 6:
            transfer();
            break;
          case 7:
            displayTransactionHistory();
            break;
          default:
            System.out.println("Opção inválida");
        }
      }
      
    }
  }

  private static void showWelcomeMenu() {
    clearScreen();
    divider();
    System.out.println("Bem vindo ao banco Maut!");
    divider();
    System.out.println("[1] - Criar conta");
    System.out.println("[2] - Acessar conta");
    System.out.println("[0] - Sair");
    divider();
  }

  private static void showUserMenu() {
    clearScreen();
    divider();
    System.out.println("Bem vindo, " + currentAccount.getUser().getName() + "!");
    divider();
    System.out.println("Seu saldo é: " + currentAccount.getBalance());
    divider();
    System.out.println("[1] - Ver informações da conta");
    System.out.println("[2] - Ver informações do usuário");
    System.out.println("[3] - Ver saldo");
    System.out.println("[4] - Depositar");
    System.out.println("[5] - Sacar");
    System.out.println("[6] - Transferir");
    System.out.println("[7] - Ver extrato");
    System.out.println("[0] - Sair");
    divider();
  }

  private static void clearScreen() {
    System.out.println("\033[H\033[2J");
    System.out.flush();
  }

  private static void exit() {
    System.out.println("Obrigado por usar o banco Maut!");
    System.exit(0);
  }

  private static void createAccount() {
    System.out.println("Digite seu CPF: ");
    String cpf = scanner.next();

    User user = verifyUser(cpf);

    if (user == null) {
      Date birthDate;

      System.out.println("Digite seu nome: ");
      String name = scanner.next();

      while (true) {
        System.out.println("Digite sua data de nascimento (dd/MM/yyyy): ");
        String dateStr = scanner.next();
        birthDate = parseDate(dateStr);
        if (birthDate != null) {
          break;
        }
      }

      System.out.println("Digite o nome da sua rua: ");
      String street = scanner.next();
      System.out.println("Digite o número da sua casa: ");
      String number = scanner.next();
      System.out.println("Digite o nome da sua cidade: ");
      String city = scanner.next();
      System.out.println("Digite o nome do seu estado: ");
      String state = scanner.next();

      System.out.println("Digite o número da sua agência: ");
      String agency = scanner.next();

      Address address = new Address(street, number, city, state);
      User newUser = new User(name, cpf, birthDate, address);
      Account account = new Account(agency, newUser);

      users.add(newUser);
      accounts.add(account);

      System.out.println("Conta criada com sucesso!");
    } else {
      System.out.println("Digite o número da sua agência: ");
      String agency = scanner.next();

      Account account = new Account(agency, user);
      accounts.add(account);

      System.out.println("Informações da sua conta: ");
      System.out.println(account);

      System.out.println("Conta criada com sucesso!");
    }
  }

  private static void accessAccount() {
    System.out.println("Digite o número da sua conta: ");
    String accountNumber = scanner.next();

    System.out.println("Digite o número da sua agência: ");
    String agency = scanner.next();

    for (Account account : accounts) {
      if (account.getNumber().equals(accountNumber) && account.getAgency().equals(agency)) {
        currentAccount = account;
        break;
      }
    }
    if (currentAccount == null) {
      System.out.println("Conta não encontrada");
      pause();
    }
  }

  private static void displayAccountInfo() {
    clearScreen();
    System.out.println("Informações da sua conta: ");
    System.out.println(currentAccount);

    pause();
  }

  private static void displayUserInfo() {
    clearScreen();
    System.out.println("Informações do usuário: ");
    System.out.println(currentAccount.getUser());

    pause();
  }

  private static void displayBalance() {
    clearScreen();
    System.out.println("Seu saldo é: " + currentAccount.getBalance());

    pause();
  }

  private static void deposit() {
    clearScreen();
    System.out.println("Digite o valor a ser depositado: ");
    double value = scanner.nextDouble();

    Transaction transaction = new Transaction(currentAccount);
    transaction.deposit(currentAccount, value);

    System.out.println("Depósito realizado com sucesso!");
    transactions.add(transaction);
    pause();
  }

  private static void withdraw() {
    clearScreen();
    System.out.println("Digite o valor a ser sacado: ");
    double value = scanner.nextDouble();

    if (currentAccount.withdraw(value)) {
      System.out.println("Saque realizado com sucesso!");
      Transaction transaction = new Transaction(currentAccount);
      transaction.withdraw(currentAccount, value);
      transactions.add(transaction);
    } else {
      System.out.println("Valor inválido");
    }

    pause();
  }

  private static void transfer() {
    clearScreen();
    System.out.println("Digite o número da conta de destino: ");
    String accountNumber = scanner.next();

    System.out.println("Digite o número da agência de destino: ");
    String agency = scanner.next();

    Account destinationAccount = null;

    for (Account account : accounts) {
      if (account.getNumber().equals(accountNumber) && account.getAgency().equals(agency)) {
        destinationAccount = account;
        break;
      }
    }

    if (destinationAccount == null) {
      System.out.println("Conta não encontrada");
    } else {
      System.out.println("Digite o valor a ser transferido: ");
      double value = scanner.nextDouble();

      Transaction transaction = new Transaction(currentAccount);
      Transaction userDestinationTransaction = new Transaction(destinationAccount);
      
      if (transaction.transfer(destinationAccount, value)) {
        System.out.println("Transferência realizada com sucesso!");
        transactions.add(transaction);
      } else {
        System.out.println("Valor inválido");
      }

      if (userDestinationTransaction.deposit(destinationAccount, value)) {
        transactions.add(userDestinationTransaction);
      }
    }

    pause();
  }

  private static void displayTransactionHistory() {
    clearScreen();
    System.out.println("Extrato: ");
    for (Transaction transaction : transactions) {
      if (transaction.getUserAccount().equals(currentAccount)) {
        System.out.println(transaction);
      }
    }

    pause();
  }

  private static User verifyUser(String cpf) {
    for (User user : users) {
      if (user.getCpf().equals(cpf)) {
        return user;
      }
    }
    return null;
  }

  private static Date parseDate(String dateStr) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    try {
      return formatter.parse(dateStr);
    } catch (ParseException e) {
      System.out.println("Data inválida, tente novamente");
      return null;
    }
  }

  private static void pause() {
    scanner.nextLine(); // Consume newline left-over
    System.out.println("Pressione enter para continuar...");
    scanner.nextLine();
  }

  private static void divider() {
    System.out.println("==============================================");
  }
}
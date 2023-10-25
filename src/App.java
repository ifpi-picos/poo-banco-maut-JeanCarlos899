import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.edu.ifpi.poo.entities.Account;
import br.edu.ifpi.poo.entities.Address;
import br.edu.ifpi.poo.entities.Client;
import br.edu.ifpi.poo.entities.CurrentAccount;
import br.edu.ifpi.poo.entities.Transaction;
import br.edu.ifpi.poo.notifications.Notifications;
import br.edu.ifpi.poo.notifications.NotificationsEmail;
import br.edu.ifpi.poo.notifications.NotificationsSms;

// Algumas informações importantes antes de começar:
// * O acesso a conta é feito através do número da conta, que é gerado automaticamente;
// * O número da conta é gerado automaticamente, e é composto por 4 dígitos;
// * O número da agência é informado pelo usuário;
// * O saldo inicial é 0;
// * O cheque especial funciona da seguinte maneira:
//   - O valor do cheque especial é 0 por padrão;
//   - O valor do cheque especial apesar de ser um valor positivo, é considerado uma dívida;
//   - O valor do cheque especial é usado para cobrir saques e transferências, caso o saldo seja insuficiente;
//   - Ao fazer um depósito, o valor do cheque especial é usado para reduzir a dívida, caso haja.
// * Algumas implementações no App como a parte de edição dos dados não foram inseridas pois não agregam no aprendizado;
// * Provavelmente existem diversos bugs, pois não foi feito testes suficientes;
// * Tratativas de erros por parte do usuário não foram implementadas pois aumentariam a complexidade do código;


public class App {
  private static List<Account> accounts = new ArrayList<>();
  private static List<Client> clients = new ArrayList<>();

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    Account currentAccount = null;

    while (true) {
      clearScreen();
      System.out.println("==========Bem-vindo ao Banco Maut!==========");
      System.out.println("[1] Criar conta");
      System.out.println("[2] Acessar conta");
      System.out.println("[3] Sair");
      System.out.println("============================================");

      int input = scanner.nextInt();

      switch (input) {
        case 1:
          Notifications notifications = null;
          Client client = null;
          String agency;
          String cpf;
          int notificationType;

          clearScreen();
          System.out.println("=================Criar conta================");
          System.out.println("[1] Conta corrente");
          System.out.println("[2] Conta poupança");
          System.out.println("[3] sair");

          int accountType = scanner.nextInt();
          scanner.nextLine();

          switch (accountType) {
            case 1:
              clearScreen();
              System.out.println("============Criar conta corrente============");
              System.out.println("Digite o seu CPF: ");
              cpf = scanner.nextLine();

              client = findClientByCpf(clients, cpf);

              if (client == null) {
                client = createClient(scanner, cpf);
                clients.add(client);
              }

              System.out.println("============Informações da conta============");

              System.out.println("Digite o número da agência: ");
              agency = scanner.nextLine();

              System.out.println("Notificações por e-mail ou SMS? ");
              System.out.println("[1] E-mail");
              System.out.println("[2] SMS");
              notificationType = scanner.nextInt();

              notifications = getNotifications(notificationType);

              Account newCurrentAccount = new CurrentAccount(agency, client, notifications);
              accounts.add(newCurrentAccount);

              createdMessage(scanner, newCurrentAccount);
              break;
            case 2:
              clearScreen();
              System.out.println("============Criar conta poupança============");
              System.out.println("Digite o seu CPF: ");
              cpf = scanner.nextLine();

              client = findClientByCpf(clients, cpf);

              if (client == null) {
                client = createClient(scanner, cpf);
                clients.add(client);
              } else {
                System.out.println("Bem vindo de volta, " + client.getName() + "!");
              }

              System.out.println("============Informações da conta============");

              System.out.println("Digite o número da agência: ");
              agency = scanner.nextLine();

              System.out.println("Notificações por e-mail ou SMS? ");
              System.out.println("[1] E-mail");
              System.out.println("[2] SMS");
              notificationType = scanner.nextInt();

              notifications = getNotifications(notificationType);

              Account newSavingsAccount = new CurrentAccount(agency, client, notifications);
              accounts.add(newSavingsAccount);

              createdMessage(scanner, newSavingsAccount);
              break;

            case 3:
              System.out.println("Saindo...");
              break;

            default:
              System.out.println("Opção inválida.");
          }
          break;

        case 2:
          scanner.nextLine();
          clearScreen();
          System.out.println("=================Acessar conta==============");
          System.out.println("Digite o número da conta: ");
          String accountNumber = scanner.nextLine();

          currentAccount = findAccountByNumber(accounts, accountNumber);

          if (currentAccount != null) {
            performAccountOperations(currentAccount, scanner);
          } else {
            System.out.println("============================================");
            System.out.println("Conta não encontrada.");
            System.out.println("============================================");
            wait(scanner);
          }
          break;

        case 3:
          System.out.println("Saindo...");
          return;
      }
    }
  }

  public static void performAccountOperations(Account account, Scanner scanner) {
    while (true) {
      clearScreen();
      System.out.println("===========Bem-vindo à sua conta!===========");
      System.out.println("Saldo: R$ " + account.getBalance());
      if (account instanceof CurrentAccount) {
        System.out.println("Cheque especial usado: R$ " + ((CurrentAccount) account).getOverdraft());
      }
      System.out.println("============================================");
      System.out.println("[1] Depositar");
      System.out.println("[2] Sacar");
      System.out.println("[3] Transferir");
      System.out.println("[4] Ver extrato");
      System.out.println("[5] ver informações da conta");
      System.out.println("[6] Sair da conta");

      int choice = scanner.nextInt();
      scanner.nextLine();
      double amount;

      switch (choice) {
        case 1:
          clearScreen();
          System.out.println("===============Depósito=====================");
          System.out.print("Digite o valor a ser depositado: ");
          amount = scanner.nextDouble();
          account.deposit(amount);
          System.out.println("Depósito de " + amount + " realizado com sucesso.");
          break;

        case 2:
          clearScreen();
          System.out.println("===============Saque=======================");
          System.out.print("Digite o valor a ser sacado: ");
          amount = scanner.nextDouble();
          account.withdraw(amount);
          System.out.println("Saque de " + amount + " realizado com sucesso.");
          break;

        case 3:
          clearScreen();
          System.out.println("===============Transferência================");
          System.out.print("Digite o número da conta de destino: ");
          String destAccountNumber = scanner.nextLine();
          Account destAccount = findAccountByNumber(accounts, destAccountNumber);

          if (destAccount != null) {
            System.out.print("Digite o valor a ser transferido: ");
            amount = scanner.nextDouble();
            account.transfer(destAccount, amount);
            System.out.println("Transferência de " + amount + " realizada com sucesso.");
          } else {
            System.out.println("Conta de destino não encontrada.");
          }
          break;

        case 4:
          clearScreen();
          System.out.println("==============Extrato da conta==============");
          if (account.getTransactions().isEmpty()) {
            System.out.println("Não há transações.");
          } else {
            for (Transaction transaction : account.getTransactions()) {
              System.out.println(transaction);
            }
          }
          wait(scanner);
          break;

        case 5:
          clearScreen();
          System.out.println("============Informações da conta============");
          System.out.println(account);
          wait(scanner);
          break;

        case 6:
          System.out.println("Saindo da conta...");
          return;

        default:
          System.out.println("Opção inválida.");
      }
    }
  }

  private static void createdMessage(Scanner scanner, Account account) {
    clearScreen();
    System.out.println("============Informações da conta============");
    System.out.println(account);
    System.out.println("============================================");

    wait(scanner);
  }

  private static void wait(Scanner scanner) {
    scanner.nextLine();
    System.out.print("Pressione qualquer tecla para continuar...");
    scanner.nextLine();
  }

  private static Notifications getNotifications(int notificationType) {
    Notifications notifications = null;
    if (notificationType == 1) {
      notifications = new NotificationsEmail();
    } else if (notificationType == 2) {
      notifications = new NotificationsSms();
    } else {
      System.out.println("Opção inválida.");
    }
    return notifications;
  }

  private static Client createClient(Scanner scanner, String cpf) {
    Client client;
    System.out.println("Digite o seu nome: ");
    String name = scanner.nextLine();

    Date birthDate;
    while (true) {
      System.out.println("Digite sua data de nascimento (dd/MM/yyyy): ");
      String dateStr = scanner.next();
      birthDate = parseDate(dateStr);
      if (birthDate != null) {
        break;
      }
    }
    System.out.println("===========Informe o seu endereço===========");
    System.out.println("Digite o nome da sua rua: ");
    String street = scanner.next();
    scanner.nextLine();
    System.out.println("Digite o número da sua casa: ");
    String number = scanner.next();
    scanner.nextLine();
    System.out.println("Digite o nome do seu bairro: ");
    String neighborhood = scanner.next();
    scanner.nextLine();
    System.out.println("Digite o nome da sua cidade: ");
    String city = scanner.next();
    scanner.nextLine();
    System.out.println("Digite a sigla do seu estado: ");
    String state = scanner.next();
    scanner.nextLine();

    Address address = new Address(street, number, neighborhood, city, state);
    client = new Client(cpf, name, birthDate, address);

    return client;
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static Client findClientByCpf(List<Client> clients, String cpf) {
    for (Client client : clients) {
      if (client.getCpf().equals(cpf)) {
        return client;
      }
    }

    return null;
  }

  public static Account findAccountByNumber(List<Account> accounts, String accountNumber) {
    for (Account account : accounts) {
      if (account.getAccountNumber().equals(accountNumber)) {
        return account;
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
}
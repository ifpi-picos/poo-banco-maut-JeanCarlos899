import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import components.IOComponent;
import components.InterfaceComponent;
import model.Account;
import model.Transaction;
import model.User;

// Atividade de POO, banco Maut - Jean Carlos Rodrigues Sousa
// Análise e Desenvolvimento de Sistemas - 2023.2 - IFPI CAPIC
// Professor: Jesiel Viana

public class App {
  public static void main(String[] args) throws Exception {

    // instância do Scanner para ler os dados do usuário
    // esta instância é passada para os métodos que precisam
    // ler dados do usuário

    Scanner scanner = new Scanner(System.in);

    // lista dos usuários, contas e transações
    List<Account> accounts = new ArrayList<Account>();
    List<User> users = new ArrayList<User>();
    List<Transaction> transactions = new ArrayList<Transaction>();
    // usuário que está logado no sistema
    User currentUser = null;

    users.add(new User("Jean",
        "12345678910",
        null,
        null,
        "123456"));
    users.add(new User("Karielly",
        "10987654321",
        null,
        null,
        "123456"));

    while (true) {

      // o projeto está dividido em 3 pacotes: components, model e App.
      // o pacote components contém as classes que são responsáveis por
      // interagir com o usuário. O pacote model contém as classes que
      // representam os objetos do sistema

      // menu principal
      InterfaceComponent.divider();
      InterfaceComponent.showMenu();
      InterfaceComponent.divider();

      // opção escolhida pelo usuário
      int option = scanner.nextInt();

      switch (option) {
        case 0:
          scanner.close();
          System.exit(0);
          break;
        case 1:
          User user = IOComponent.createUser(scanner);
          users.add(user);
          break;
        case 2:
          String[] accessData = IOComponent.accessUser(scanner);
          String cpf = accessData[0];
          String password = accessData[1];

          for (User u : users) {
            if (u.getCpf().equals(cpf) &&
                u.getPassword().equals(password)) {
              currentUser = u;
              break;
            }
          }

          if (currentUser == null) {
            System.out.println("Usuário não encontrado");
            break;
          }

          InterfaceComponent.showMenuUser(currentUser);
          int optionUser = scanner.nextInt();

          switch (optionUser) {
            case 0:
              scanner.close();
              System.exit(0);
              break;
            case 1:
              Account account = IOComponent.createAccount(
                  scanner,
                  currentUser);

              accounts.add(account);
              break;
            case 2:
              Account accountAccess = IOComponent.accessAccount(
                  scanner,
                  accounts,
                  currentUser);

              if (accountAccess == null) {
                System.out.println("Conta não encontrada");
                break;
              } else {
                InterfaceComponent.showMenuAccount();
                int optionAccount = scanner.nextInt();

                switch (optionAccount) {
                  case 0:
                    scanner.close();
                    System.exit(0);
                    break;
                  case 1:
                    Transaction transaction = IOComponent.deposit(scanner, accountAccess);
                    transactions.add(transaction);
                    break;
                  case 2:
                    Transaction transactionWithdraw = IOComponent.withdraw(scanner, accountAccess);
                    transactions.add(transactionWithdraw);
                    break;
                  case 3:
                    IOComponent.transfer(scanner, accountAccess, accounts);
                    break;
                  case 4:
                    IOComponent.extract(accountAccess, transactions);
                    break;
                  default:
                    break;
                }
                break;
              }
            default:
              break;
          }

        default:
          break;
      }
    }
  }
}

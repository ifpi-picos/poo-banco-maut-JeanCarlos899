import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import components.IOComponent;
import components.InterfaceComponent;
import model.Account;
import model.User;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<Account> accounts = new ArrayList<Account>();
        List<User> users = new ArrayList<User>();
        User currentUser = null;

        while (true) {
            InterfaceComponent.divider();
            InterfaceComponent.showMenu();
            InterfaceComponent.divider();
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
                        if (u.getCpf().equals(cpf) && u.getPassword().equals(password)) {
                            currentUser = u;
                            break;
                        }
                    }

                    if (currentUser == null) {
                        System.out.println("Usuário não encontrado");
                        break;
                    } else {
                        System.out.println("Usuário encontrado");
                        System.out.println("Bem vindo, " + currentUser.getName());
                    }
                    break;

                default:
                    break;
            }
        } 
    }
}

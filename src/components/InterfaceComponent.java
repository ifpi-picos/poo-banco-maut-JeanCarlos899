package components;

import model.User;

public class InterfaceComponent {
  public static void showMenu() {
    System.out.println("Bem vindo ao Banco do Maut!");
    System.out.println("[1] - Criar usuário");
    System.out.println("[2] - Acessar");
    System.out.println("[0] - Sair");
  }

  public static void showMenuUser(User currentUser) {
    InterfaceComponent.divider();
    System.out.println("Bem-vindo ao Banco Maut!" + currentUser.getName());
    InterfaceComponent.divider();
    System.out.println("[1] - Criar conta");
    System.out.println("[2] - Acessar conta");
    System.out.println("[0] - Sair");
    InterfaceComponent.divider();
  }

  public static void showMenuAccount() {
    InterfaceComponent.divider();
    System.out.println("[1] - Depositar");
    System.out.println("[2] - Sacar");
    System.out.println("[3] - Transferir");
    System.out.println("[4] - Extrato");
    System.out.println("[0] - Sair");
    InterfaceComponent.divider();
  }

  public static void divider() {
    System.out.println("========================================");
  }
}

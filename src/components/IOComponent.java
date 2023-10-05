package components;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.Address;
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
}

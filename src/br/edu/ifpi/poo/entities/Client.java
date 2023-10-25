package br.edu.ifpi.poo.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    final private String cpf;
    private String name;
    private Date birthDate;
    private Address address;

    public Client(String cpf, String name, Date birthDate, Address address) {
        this.cpf = cpf;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(Date birthDate) {
        if (birthDate.after(new Date())) {
            throw new IllegalArgumentException("Data de nascimento não pode ser maior que a data atual");
        }
        this.birthDate = birthDate;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return """
                CPF: %s
                Nome: %s
                Data de Nascimento: %s
                Endereço: %s
                """.formatted(cpf, name, dateFormat.format(birthDate), address);
    }
}

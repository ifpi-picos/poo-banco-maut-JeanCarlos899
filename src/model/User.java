package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String name;
    private final String cpf;
    private Date birthDate;
    private Address address;

    public User(String name, String cpf, Date birthDate, Address address) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
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
        this.birthDate = birthDate;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedBirthDate = dateFormat.format(birthDate);

        return String.format("""
                Nome: %s
                CPF: %s
                Data de nascimento: %s
                """, name, cpf, formattedBirthDate);
    }

}

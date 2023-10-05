package model;

import java.util.Date;
import java.util.UUID;

public class User {
    private UUID uid;
    private String name;
    private String cpf;
    private Date birthDate;
    private Address address;
    private String password;

    public User(String name, String cpf, Date birthDate, Address address, String password) {
        this.uid = UUID.randomUUID();
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.address = address;
        this.password = password;
    }

    public UUID getUid() {
        return uid;
    }
    
    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPassword() {
        return password;
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
}

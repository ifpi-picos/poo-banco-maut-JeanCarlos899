package br.edu.ifpi.poo.entities;

public class Address {
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;

    public Address(String street, String number, String neighborhood, String city, String state) {
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }
}

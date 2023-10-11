package model;

public class Address {
    private String street;
    private String number;
    private String city;
    private String state;

    public Address(String street, String number, String city, String state) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Endereço [rua=" + street + "\n, número=" + number + "\n, cidade=" + city + "\n, estado=" + state + "]";
    }
}

package model;

public class Address {
    private String street;
    private String number;
    private String city;
    private String state;
    private String complement;

    public Address(String street, String number, String city, String state, String complement) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.complement = complement;
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

    public String getComplement() {
        return complement;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setState(String state) {
        this.state = state;
    }
}

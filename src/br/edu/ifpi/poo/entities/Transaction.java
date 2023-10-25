package br.edu.ifpi.poo.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private Date date;
    private String type;
    private double value;

    public Transaction(String type, double value) {
        this.date = new Date();
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        return "* %s feito em %s no valor de R$ %.2f".formatted(this.type, dateFormat.format(this.date), this.value);
    }
}
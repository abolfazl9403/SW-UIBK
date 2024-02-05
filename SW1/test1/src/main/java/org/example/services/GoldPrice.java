package org.example.services;
import java.time.LocalDate;
public class GoldPrice {
    private LocalDate date;
    private double price;

    public GoldPrice(LocalDate date, double price) {
        this.date = date;
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "GoldPrice{" +
                "Date = " + date +
                ", Price = " + price +
                '}';
    }
}

package com.example.megacitycab.model;

import java.sql.Timestamp;

public class Payment {

    private int paymentId;
    private int bookingId;
    private String paymentMethod; // CASH, CARD
    private double amount;
    private Timestamp paymentTime;

    public Payment() {}
    public Payment(int bookingId, String paymentMethod, double amount) {
        this.bookingId = bookingId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }
    public Payment(int paymentId, int bookingId, double amount, String paymentMethod, Timestamp paymentTime) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentTime = paymentTime;
    }
    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public Timestamp getPaymentTime() { return paymentTime; }
    public void setPaymentTime(Timestamp paymentTime) { this.paymentTime = paymentTime; }
}

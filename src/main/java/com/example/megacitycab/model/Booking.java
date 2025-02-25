package com.example.megacitycab.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Booking {

    private int bookingId;
    private int userId;
    private int vehicleId;
    private String pickupLocation;
    private String dropoffLocation;
    private double distance;
    private double fare;
    private String status; // PENDING, CONFIRMED, COMPLETED, CANCELLED
    private Timestamp bookingTime;
    private String paymentMethod; // NEW FIELD
    private String transactionId; // NEW FIELD



    public Booking() {}

    public Booking(int userId, int vehicleId, String pickupLocation, String dropoffLocation, double distance, double fare, String status) {
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.distance = distance;
        this.fare = fare;
        this.status = status;
    }
    public Booking(int bookingId, int userId, int vehicleId, String pickupLocation, String dropoffLocation, double distance, double fare, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.distance = distance;
        this.fare = fare;
        this.status = status;
    }
    public Booking(int bookingId, int userId, String pickupLocation, String dropoffLocation,
                   double distance, double fare, String status, Timestamp bookingTime) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.distance = distance;
        this.fare = fare;
        this.status = status;
        this.bookingTime = bookingTime;
    }
    public Booking(int bookingId, String pickupLocation, String dropoffLocation,
                   double distance, double fare, String paymentMethod, String transactionId) {
        this.bookingId = bookingId;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.distance = distance;
        this.fare = fare;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
    }

    public Booking(int bookingId, int userId, int vehicleId, String pickupLocation, String dropoffLocation, double distance, double fare, String status, Timestamp bookingTime) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.distance = distance;
        this.fare = fare;
        this.status = status;
        this.bookingTime = bookingTime;
    }


    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }

    public String getDropoffLocation() { return dropoffLocation; }
    public void setDropoffLocation(String dropoffLocation) { this.dropoffLocation = dropoffLocation; }

    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }

    public double getFare() { return fare; }
    public void setFare(double fare) { this.fare = fare; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Timestamp getBookingTime() { return bookingTime; }
    public void setBookingTime(Timestamp bookingTime) { this.bookingTime = bookingTime; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getTransactionId() { return transactionId; }
}

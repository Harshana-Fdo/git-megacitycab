package com.example.megacitycab.service;


import com.example.megacitycab.dao.DatabaseConnection;
import com.example.megacitycab.dao.PaymentDAO;
import com.example.megacitycab.model.Booking;
import com.example.megacitycab.model.Payment;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PaymentService {

    private PaymentDAO paymentDAO;



    public PaymentService() {
        paymentDAO = new PaymentDAO();



    }

    public List<Payment> getAllPayments() {
        return paymentDAO.getAllPayments();
    }

    // ✅ Get total earnings from all payments
    public double getTotalEarnings() {
        return paymentDAO.getTotalEarnings();
    }

    // ✅ Get total earnings within a date range using `payment_time`
    public double getEarningsByDateRange(String startDate, String endDate) {
        return paymentDAO.getEarningsByDateRange(startDate, endDate);
    }

    public List<Booking> getUnpaidBookings(int userId) {
        return paymentDAO.getUnpaidBookings(userId);
    }
    public Booking getBookingDetails(int bookingId) {
        return paymentDAO.getBookingDetails(bookingId);
    }

    public List<Payment> getPaymentsByUser(int userId) {
        return paymentDAO.getPaymentsByUser(userId);
    }
    public boolean processPayment(int bookingId, String paymentMethod) {
        return paymentDAO.insertPayment(bookingId, paymentMethod);


    }




}

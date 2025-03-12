package com.example.megacitycab.dao;

import com.example.megacitycab.model.Booking;
import com.example.megacitycab.model.Payment;
import com.example.megacitycab.service.observer.EmailNotification;
import com.example.megacitycab.service.observer.SMSNotification;
import com.example.megacitycab.service.observer.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO extends Subject {

    private Connection conn;

    public PaymentDAO() {
        this.conn = DatabaseConnection.getConnection();
        addObserver(new EmailNotification());
        addObserver(new SMSNotification());
    }


    public boolean insertPayment(int bookingId, String paymentMethod) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DatabaseConnection.getConnection();

            // 🔹 Step 1: Insert payment record
            String insertQuery = "INSERT INTO payments (booking_id, payment_method, amount, payment_time) " +
                    "VALUES (?, ?, (SELECT fare FROM bookings WHERE booking_id = ?), NOW())";
            ps = conn.prepareStatement(insertQuery);
            ps.setInt(1, bookingId);
            ps.setString(2, paymentMethod);
            ps.setInt(3, bookingId);
            int rowsAffected = ps.executeUpdate();

            System.out.println("row affected: "+rowsAffected);
            // 🔹 Step 2: Update booking status to "PAID"
            if (rowsAffected > 0) {
                String updateQuery = "UPDATE bookings SET status = 'PAID' WHERE booking_id = ?";
                ps = conn.prepareStatement(updateQuery);
                ps.setInt(1, bookingId);
                ps.executeUpdate();
                String message = "✅ Payment Successful! Booking ID: " + bookingId + ", Method: " + paymentMethod;
                notifyObservers(message);  // 🔔 Notify all observers

                return true; // ✅ Payment successful
            }

        } catch (SQLException e) {
            e.printStackTrace(); // 🔹 Print error logs
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException ignored) {}
        }
        return false; // ❌ Payment failed
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        try {
            String sql = "SELECT * FROM payments";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Payment payment = new Payment(
                        rs.getInt("payment_id"),
                        rs.getInt("booking_id"),
                        rs.getDouble("amount"),
                        rs.getString("payment_method"),
                        rs.getTimestamp("payment_date")
                );
                payments.add(payment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payments;
    }

    // ✅ Get total earnings
    public double getTotalEarnings() {
        double total = 0;
        try {
            String sql = "SELECT SUM(amount) FROM payments";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    // ✅ Get earnings for a specific date range
    public double getEarningsByDateRange(String startDate, String endDate) {
        double total = 0;
        try {
            // ✅ Extracts only the date part from `payment_time`
            String sql = "SELECT COALESCE(SUM(amount), 0) FROM payments WHERE DATE(payment_time) BETWEEN ? AND ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("🔍 Debug: Earnings between " + startDate + " and " + endDate + " = $" + total);
        return total;
    }

    public List<Booking> getUnpaidBookings(int userId) {
        List<Booking> unpaidBookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE user_id = ? AND booking_id NOT IN (SELECT booking_id FROM payments)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("user_id"),
                        rs.getString("pickup_location"),
                        rs.getString("dropoff_location"),
                        rs.getDouble("distance"),
                        rs.getDouble("fare"),
                        rs.getString("status"),
                        rs.getTimestamp("booking_time")
                );
                unpaidBookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unpaidBookings;
    }

    public Booking getBookingDetails(int bookingId) {
        Booking booking = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT b.booking_id, b.pickup_location, b.dropoff_location, " +
                    "b.distance, b.fare, p.payment_method, p.transaction_id " +
                    "FROM bookings b LEFT JOIN payments p ON b.booking_id = p.booking_id " +
                    "WHERE b.booking_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                booking = new Booking(
                        rs.getInt("booking_id"),
                        rs.getString("pickup_location"),
                        rs.getString("dropoff_location"),
                        rs.getDouble("distance"),
                        rs.getDouble("fare"),
                        rs.getString("payment_method"),
                        rs.getString("transaction_id")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booking;
    }
    public List<Payment> getPaymentsByUser(int userId) {
        List<Payment> payments = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM payments WHERE booking_id IN (SELECT booking_id FROM bookings WHERE user_id = ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setBookingId(rs.getInt("booking_id"));
                payment.setPaymentMethod(rs.getString("payment_method"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentTime(rs.getTimestamp("payment_time"));
                payments.add(payment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payments;
    }
    public boolean processPayment(int bookingId, String paymentMethod) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DatabaseConnection.getConnection();

            // Insert payment into payments table
            String insertPayment = "INSERT INTO payments (booking_id, payment_method, amount, payment_time) " +
                    "VALUES (?, ?, (SELECT fare FROM bookings WHERE booking_id = ?), NOW())";
            ps = conn.prepareStatement(insertPayment);
            ps.setInt(1, bookingId);
            ps.setString(2, paymentMethod);
            ps.setInt(3, bookingId);
            int rowsInserted = ps.executeUpdate();

            // Update booking status to "PAID"
            if (rowsInserted > 0) {
                String updateBooking = "UPDATE bookings SET status = 'PAID' WHERE booking_id = ?";
                ps = conn.prepareStatement(updateBooking);
                ps.setInt(1, bookingId);
                ps.executeUpdate();
                return true; // Payment successful
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException ignored) {}
        }
        return false; // Payment failed
    }

}


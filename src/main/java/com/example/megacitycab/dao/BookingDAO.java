package com.example.megacitycab.dao;

import com.example.megacitycab.model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    private Connection conn;

    public BookingDAO() {
        this.conn = DatabaseConnection.getConnection();
    }

    // Insert new booking into database
    public int insertBooking(int userId, int vehicleId, String pickupLocation, String dropoffLocation, double distance) {
        int bookingId = -1;
        String sql = "INSERT INTO bookings (user_id, vehicle_id, pickup_location, dropoff_location, distance, fare, status) VALUES (?, ?, ?, ?, ?, ?, 'PENDING')";

        try  {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            double fare = distance * 2.5; // ✅ Example fare calculation: $2.5 per km

            ps.setInt(1, userId);
            ps.setInt(2, vehicleId);
            ps.setString(3, pickupLocation);
            ps.setString(4, dropoffLocation);
            ps.setDouble(5, distance);
            ps.setDouble(6, fare);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    bookingId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingId;
    }

    // Retrieve all bookings for a specific customer

    // Update booking status (e.g., Confirmed, Completed, Cancelled)

    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        try {
            String sql = "SELECT * FROM bookings";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("user_id"),
                        rs.getInt("vehicle_id"),
                        rs.getString("pickup_location"),
                        rs.getString("dropoff_location"),
                        rs.getDouble("distance"),
                        rs.getDouble("fare"),
                        rs.getString("status")
                );
                bookings.add(booking);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }
    public List<Booking> getBookingsByUser(int user) {
        List<Booking> bookings = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM bookings WHERE user_id = ? ORDER BY booking_time DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("user_id"),
                        rs.getInt("vehicle_id"),
                        rs.getString("pickup_location"),
                        rs.getString("dropoff_location"),
                        rs.getDouble("distance"),
                        rs.getDouble("fare"),
                        rs.getString("status"),
                        rs.getTimestamp("booking_time")

                );
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
    public List<Booking> getPendingBookingsByUser(int userId) {
        List<Booking> bookings = new ArrayList<>();
        try {
            String sql = "SELECT * FROM bookings WHERE user_id = ? AND status = 'PENDING'";
            PreparedStatement ps = conn.prepareStatement(sql);
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
                bookings.add(booking);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }
    public Booking getBookingById(int bookingId) {
        Booking booking = null;
        try {
            String sql = "SELECT * FROM bookings WHERE booking_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                booking = new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("user_id"),
                        rs.getString("pickup_location"),
                        rs.getString("dropoff_location"),
                        rs.getDouble("distance"),
                        rs.getDouble("fare"),
                        rs.getString("status"),
                        rs.getTimestamp("booking_time")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booking;
    }
    public boolean updateBookingStatus(int bookingId, String status) {
        try {
            String sql = "UPDATE bookings SET status = ? WHERE booking_id = ?";
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, bookingId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

package com.example.megacitycab.service;

import com.example.megacitycab.dao.BookingDAO;
import com.example.megacitycab.dao.VehicleDAO;
import com.example.megacitycab.model.Booking;
import com.example.megacitycab.model.Vehicle;


import java.util.List;

public class BookingService {
    private VehicleDAO vehicleDAO = new VehicleDAO();
    private BookingDAO bookingDAO;

    public BookingService() {
        this.bookingDAO = new BookingDAO();

    }

    // Creates a new booking


    // Retrieves customer bookings
    public List<Booking> getCustomerBookings(int userId) {
        return bookingDAO.getBookingsByUser(userId);
    }

    // Updates booking status (Admin feature)

    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }

    public boolean updateBookingStatus(int bookingId, String status) {
        return bookingDAO.updateBookingStatus(bookingId, status);
    }



    public List<Booking> getPendingBookingsByUser(int userId) {
        return bookingDAO.getPendingBookingsByUser(userId);
    }


    public boolean confirmBooking(int bookingId) {

        return bookingDAO.updateBookingStatus(bookingId, "CONFIRMED");

    }
    public List<Vehicle> getAvailableVehicles() {
        return vehicleDAO.getAvailableVehicles();
    }


    public List<Booking> getBookingsByUser(int userId) {

        return bookingDAO.getBookingsByUser(userId);
    }
    public Booking getBookingById(int bookingId) {
        return bookingDAO.getBookingById(bookingId);
    }
    public int createBooking(int userId, int vehicleId, String pickupLocation, String dropoffLocation, double distance) {
        if (userId <= 0 || vehicleId <= 0 || pickupLocation.isEmpty() || dropoffLocation.isEmpty() || distance <= 0) {
            return 0; // Indicate failure
        }
        return bookingDAO.insertBooking(userId, vehicleId, pickupLocation, dropoffLocation, distance);
    }
    public Booking getBookingDetails(int bookingId) {
        return bookingDAO.getBookingById(bookingId);
    }




}

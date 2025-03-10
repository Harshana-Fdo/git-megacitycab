package com.example.megacitycab.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    @Test
    public void testCreateBooking_InvalidData() {
        BookingService bookingService = new BookingService();


        int result = bookingService.createBooking(-1, -1, "", "", -5.0);


        assertTrue(result <= 0, "Expected booking creation to fail, but got: " + result);
    }

    @Test
    public void testCreateBooking_ValidData() {
        BookingService bookingService = new BookingService();


        int result = bookingService.createBooking(1, 2, "Colombo", "Kandy", 115.0);


        assertTrue(result > 0);
    }
    @Test
    public void testUpdateBookingStatus() {
        BookingService bookingService = new BookingService();


        boolean result = bookingService.updateBookingStatus(9, "CONFIRMED");

        assertTrue(result, "Expected booking status update to succeed, but it failed.");
    }
}
package com.example.megacitycab.controller;

import com.example.megacitycab.dao.DatabaseConnection;
import com.example.megacitycab.model.Booking;
import com.example.megacitycab.model.Payment;
import com.example.megacitycab.service.BookingService;
import com.example.megacitycab.service.PaymentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


public class PaymentServlet extends HttpServlet {



    private BookingService bookingService = new BookingService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int bookingId;
        try {
            bookingId = Integer.parseInt(request.getParameter("booking_id"));
        } catch (NumberFormatException e) {
            response.sendRedirect("booking_history.jsp?error=invalid_booking_id");
            return;
        }

        // Retrieve booking details
        Booking booking = bookingService.getBookingDetails(bookingId);
        if (booking == null) {
            response.sendRedirect("booking_history.jsp?error=booking_not_found");
            return;
        }

        // Store booking details in session for payment page
        session.setAttribute("booking_id", booking.getBookingId());
        session.setAttribute("pickup", booking.getPickupLocation());
        session.setAttribute("dropoff", booking.getDropoffLocation());
        session.setAttribute("distance", booking.getDistance());
        session.setAttribute("fare", booking.getFare());
        session.setAttribute("booking_time", booking.getBookingTime());

        // Redirect to payment page
        response.sendRedirect("payment.jsp");
    }
    }


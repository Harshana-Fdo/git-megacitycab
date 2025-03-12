package com.example.megacitycab.controller;

import com.example.megacitycab.dao.DatabaseConnection;
import com.example.megacitycab.model.Booking;
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


public class ReceiptServlet extends HttpServlet {


    private BookingService bookingService = new BookingService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user");

        if (userId == null) {
            response.sendRedirect("login.jsp?error=not_logged_in");
            return;
        }

        // Fetch booking details
        int bookingId = Integer.parseInt(request.getParameter("booking_id"));
        Booking booking = bookingService.getBookingDetails(bookingId);

        if (booking != null) {
            // Set attributes for payment.jsp
            session.setAttribute("booking_id", booking.getBookingId());
            session.setAttribute("pickup", booking.getPickupLocation());
            session.setAttribute("dropoff", booking.getDropoffLocation());
            session.setAttribute("distance", booking.getDistance());
            session.setAttribute("fare", booking.getFare());
            session.setAttribute("status", booking.getStatus());
            session.setAttribute("booking_time", booking.getBookingTime());

            // ✅ Redirect to payment.jsp instead of booking history
            response.sendRedirect("payment.jsp");
        } else {
            response.sendRedirect("booking_history.jsp?error=booking_not_found");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not supported.");
    }
}

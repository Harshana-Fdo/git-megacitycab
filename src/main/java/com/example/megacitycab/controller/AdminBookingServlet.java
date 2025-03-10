package com.example.megacitycab.controller;

import com.example.megacitycab.model.Booking;
import com.example.megacitycab.service.BookingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class AdminBookingServlet extends HttpServlet {

    private BookingService bookingService = new BookingService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Booking> bookings = bookingService.getAllBookings();
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("admin_booking.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            int bookingId = Integer.parseInt(request.getParameter("booking_id"));
            String status = request.getParameter("status");

            boolean success = bookingService.updateBookingStatus(bookingId, status);
            if (success) {
                System.out.println("✅ Booking status updated.");
            } else {
                System.out.println("❌ Failed to update booking status.");
            }
        }

        response.sendRedirect("AdminBookingServlet");
    }
}

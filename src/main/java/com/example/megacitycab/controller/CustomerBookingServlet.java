package com.example.megacitycab.controller;

import com.example.megacitycab.model.Booking;
import com.example.megacitycab.model.User;
import com.example.megacitycab.service.BookingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class CustomerBookingServlet extends HttpServlet {


    private BookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp?error=not_logged_in");
            return;
        }

        // Fetch all bookings for this customer
        List<Booking> bookings = bookingService.getBookingsByUser(user.getUserId());
        request.setAttribute("bookings", bookings);

        // Forward the data to booking_history.jsp
        request.getRequestDispatcher("booking_history.jsp").forward(request, response);
    }
}

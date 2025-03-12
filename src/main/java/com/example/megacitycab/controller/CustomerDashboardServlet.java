package com.example.megacitycab.controller;

import com.example.megacitycab.model.Booking;
import com.example.megacitycab.model.Payment;
import com.example.megacitycab.model.Vehicle;
import com.example.megacitycab.service.BookingService;
import com.example.megacitycab.service.PaymentService;
import com.example.megacitycab.service.VehicleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class CustomerDashboardServlet extends HttpServlet {
    private BookingService bookingService = new BookingService();
    private PaymentService paymentService = new PaymentService();
    private VehicleService vehicleService = new VehicleService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user");

        if (userId == null) {
            response.sendRedirect("login.jsp?error=not_logged_in");
            return;
        }

        List<Booking> bookings = bookingService.getBookingsByUser(userId);
        List<Payment> payments = paymentService.getPaymentsByUser(userId);
        List<Vehicle> vehicles = vehicleService.getAvailableVehicles();

        request.setAttribute("bookings", bookings);
        request.setAttribute("payments", payments);
        request.setAttribute("vehicles", vehicles);
        request.getRequestDispatcher("customer_dashboard.jsp").forward(request, response);
    }
}

package com.example.megacitycab.controller;

import com.example.megacitycab.dao.DatabaseConnection;
import com.example.megacitycab.model.Booking;
import com.example.megacitycab.model.User;
import com.example.megacitycab.model.Vehicle;
import com.example.megacitycab.service.BookingService;
import com.example.megacitycab.service.VehicleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;


public class BookingServlet extends HttpServlet {
    private VehicleService vehicleService = new VehicleService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp?error=not_logged_in");
            return;
        }

        String pickup = request.getParameter("pickup_location");
        String dropoff = request.getParameter("dropoff_location");
        int vehicleId = Integer.parseInt(request.getParameter("vehicle_id"));
        double distance = Double.parseDouble(request.getParameter("distance"));

        BookingService bookingService = new BookingService();
        int bookingId = bookingService.createBooking(user.getUserId(), vehicleId, pickup, dropoff, distance);

        if (bookingId > 0) {
            System.out.println("user id bookings: " + request.getSession().getAttribute("user_id"));
            response.sendRedirect("customer_dashboard.jsp?message=booking_success");
        } else {
            response.sendRedirect("booking.jsp?error=booking_failed");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Vehicle> vehicles = vehicleService.getAvailableVehicles();

        request.setAttribute("vehicles", vehicles); // Send vehicles to JSP
        request.getRequestDispatcher("booking.jsp").forward(request, response);
    }
    }



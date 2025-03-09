package com.example.megacitycab.controller;

import com.example.megacitycab.dao.VehicleDAO;
import com.example.megacitycab.model.User;
import com.example.megacitycab.model.Vehicle;
import com.example.megacitycab.service.VehicleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class AdminVehicleServlet extends HttpServlet {

    private VehicleService vehicleService = new VehicleService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        if (user == null || !"ADMIN".equals(user.getRole())) {
            response.sendRedirect("login.jsp?error=unauthorized_access");
            return;
        }

        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        request.setAttribute("vehicles", vehicles);
        request.getRequestDispatcher("admin_vehicle.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String driverName = request.getParameter("driver_name");
            String vehicleNumber = request.getParameter("vehicle_number");
            String model = request.getParameter("model");
            String status = request.getParameter("status");

            vehicleService.addVehicle(new Vehicle(0, driverName, vehicleNumber, model, status));
        } else if ("update".equals(action)) {
            int vehicleId = Integer.parseInt(request.getParameter("vehicle_id"));
            String driverName = request.getParameter("driver_name");
            String model = request.getParameter("model");
            String status = request.getParameter("status");

            vehicleService.updateVehicle(vehicleId, driverName, model, status);
        } else if ("delete".equals(action)) {
            int vehicleId = Integer.parseInt(request.getParameter("vehicle_id"));
            vehicleService.deleteVehicle(vehicleId);
        }

        response.sendRedirect("AdminVehicleServlet");
    }
}

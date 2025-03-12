package com.example.megacitycab.controller;

import com.example.megacitycab.service.factory.UserFactory;
import com.example.megacitycab.model.User;
import com.example.megacitycab.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class RegisterServlet extends HttpServlet {

    AuthService authService = new AuthService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("full_name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); // Get Role (ADMIN or CUSTOMER)

        if (role == null || (!role.equals("CUSTOMER") && !role.equals("ADMIN"))) {
            response.sendRedirect("register.jsp?error=Invalid role");
            return;
        }

        User newUser = UserFactory.createUser(0, username, password, email, phone, fullName, role, "ACTIVE");

        if (authService.registerUser(newUser)) {
            if (role.equals("ADMIN")) {
                response.sendRedirect("admin_dashboard.jsp?success=admin_registered");
            } else {
                response.sendRedirect("login.jsp?success=registered");
            }
        } else {
            response.sendRedirect("register.jsp?error=Registration failed");
        }
    }

}

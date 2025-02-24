package com.example.megacitycab.controller;

import com.example.megacitycab.dao.DatabaseConnection;
import com.example.megacitycab.dao.UserDAO;
import com.example.megacitycab.factory.UserFactory;
import com.example.megacitycab.model.User;
import com.example.megacitycab.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class RegisterServlet extends HttpServlet {

    AuthService authService = new AuthService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("full_name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = (request.getParameter("password"));

        User newUser = UserFactory.createUser(0, username,password, email, phone, fullName, "CUSTOMER", "ACTIVE");

        if (authService.registerUser(newUser)) {
            response.sendRedirect("login.jsp?success=registered");
        } else {
            response.sendRedirect("register.jsp?error=register failed");
        }
    }


}

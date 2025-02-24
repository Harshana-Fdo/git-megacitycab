package com.example.megacitycab.controller;

import com.example.megacitycab.dao.DatabaseConnection;
import com.example.megacitycab.model.User;
import com.example.megacitycab.service.AuthService;
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


public class LoginServlet extends HttpServlet {
        AuthService authService = new AuthService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = authService.login(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            switch (user.getRole()){
                case "ADMIN":
                    request.getRequestDispatcher("admin_dashboard.jsp").forward(request, response);
                    break;
                case "CUSTOMER":
                    request.getRequestDispatcher("customer_dashboard.jsp").forward(request, response);
                    break;
            }

        }
        else {
            response.sendRedirect("login.jsp?error=test");
        }
    }
}

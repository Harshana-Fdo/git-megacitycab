package com.example.megacitycab.controller;

import com.example.megacitycab.model.User;
import com.example.megacitycab.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class AdminUserServlet extends HttpServlet {

    private UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        if (user == null || !"ADMIN".equals(user.getRole())) {
            response.sendRedirect("login.jsp?error=unauthorized_access");
            return;
        }

        List<User> users = userService.getAllCustomers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("admin_users.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            int userId = Integer.parseInt(request.getParameter("user_id"));
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            userService.updateUserDetails(userId, username, email, phone);
        } else if ("toggleStatus".equals(action)) {
            int userId = Integer.parseInt(request.getParameter("user_id"));
            String currentStatus = request.getParameter("current_status");
            String newStatus = "ACTIVE".equals(currentStatus) ? "INACTIVE" : "ACTIVE";

            userService.toggleUserStatus(userId, newStatus);
        }

        response.sendRedirect("AdminUserServlet");
    }
}

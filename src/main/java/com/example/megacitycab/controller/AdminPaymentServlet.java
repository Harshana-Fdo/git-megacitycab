package com.example.megacitycab.controller;

import com.example.megacitycab.model.Payment;
import com.example.megacitycab.service.PaymentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class AdminPaymentServlet extends HttpServlet {

    private PaymentService paymentService = new PaymentService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        double totalEarnings;
        List<Payment> payments = paymentService.getAllPayments();

        if (startDate != null && endDate != null) {
            totalEarnings = paymentService.getEarningsByDateRange(startDate, endDate);
        } else {
            totalEarnings = paymentService.getTotalEarnings(); // ✅ Get overall total earnings
        }

        request.setAttribute("payments", payments);
        request.setAttribute("totalEarnings", totalEarnings); // ✅ Ensure this is set
        request.getRequestDispatcher("admin_payments.jsp").forward(request, response);
    }
    }


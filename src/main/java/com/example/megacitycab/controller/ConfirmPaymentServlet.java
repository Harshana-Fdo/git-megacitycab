package com.example.megacitycab.controller;

import com.example.megacitycab.service.PaymentService;
import com.mysql.cj.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ConfirmPaymentServlet extends HttpServlet {
    private PaymentService paymentService = new PaymentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get Booking ID and Payment Method from Form
        int bookingId;
        try {
            bookingId = Integer.parseInt(request.getParameter("booking_id"));
        } catch (NumberFormatException e) {
            response.sendRedirect("payment.jsp?error=invalid_booking_id");
            return;
        }

        String paymentMethod = request.getParameter("payment_method");
        if (paymentMethod == null || paymentMethod.isEmpty()) {
            response.sendRedirect("payment.jsp?error=invalid_payment_method");
            return;
        }

        // Process Payment
        boolean isPaid = paymentService.processPayment(bookingId, paymentMethod);

        if (isPaid) {
            HttpSession session = request.getSession();
            session.setAttribute("payment_method", paymentMethod);
            session.setAttribute("payment_status", "paid");
            response.sendRedirect("receipt.jsp?success=payment_successful");
        } else {
            response.sendRedirect("payment.jsp?error=payment_failed");
        }
    }
}

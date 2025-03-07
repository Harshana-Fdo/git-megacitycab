<%@ page import="com.example.megacitycab.model.Booking" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.megacitycab.model.User" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/9/2025
  Time: 11:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<!-- Proceed to Payment -->

<%

    User loggedUser = (User) session.getAttribute("user");
    if (String.valueOf(loggedUser.getUserId()) == null || !"CUSTOMER".equals(loggedUser.getRole())) {
        response.sendRedirect("login.jsp?error=unauthorized_access");
    }

    List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
%>
<html>
<head>
    <title>Payment Receipt</title>

    <link rel="stylesheet" type="text/css" href="css/receipt.css">
</head>
<body>
<div class="receipt-container">
    <h2>Payment Receipt</h2>

    <p><strong>Pickup Location:</strong> <%= session.getAttribute("pickup") %></p>
    <p><strong>Dropoff Location:</strong> <%= session.getAttribute("dropoff") %></p>
    <p><strong>Booking Time:</strong> <%= session.getAttribute("booking_time") %></p>
    <p><strong>Distance:</strong> <%= session.getAttribute("distance") %> km</p>
    <p><strong>Total Fare:</strong> $<%= session.getAttribute("fare") %></p>
    <p><strong>Payment Method:</strong> <%= session.getAttribute("payment_method") %></p>
    <p><strong>Payment Status:</strong>
        <span class="payment-status <%= "PAID".equals(session.getAttribute("payment_status")) ? "paid" : "failed" %>">
            <%= session.getAttribute("payment_status") %>
        </span>
    </p>

    <form action="customer_dashboard.jsp" method="get">
        <button type="submit" class="back-button">← Back to Dashboard</button>
    </form>
</div>
</body>
</html>
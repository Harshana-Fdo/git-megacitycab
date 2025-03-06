<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/9/2025
  Time: 10:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.sql.*, com.example.megacitycab.dao.DatabaseConnection" %>
<%@ page import="com.example.megacitycab.model.Booking" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.megacitycab.model.User" %>
<%@ page session="true" %>
<%
    User loggedUser = (User) session.getAttribute("user");
    if (String.valueOf(loggedUser.getUserId()) == null || !"CUSTOMER".equals(loggedUser.getRole())) {
        response.sendRedirect("login.jsp?error=unauthorized_access");
    }
%>
<html>
<head>
    <title>Make a Payment</title>
    <link rel="stylesheet" type="text/css" href="css/payment.css">
</head>
<body>
<div class="payment-container">
    <h2>Confirm Your Payment</h2>

    <p><strong>Pickup Location:</strong> <%= session.getAttribute("pickup") %></p>
    <p><strong>Dropoff Location:</strong> <%= session.getAttribute("dropoff") %></p>
    <p><strong>Booking Time:</strong> <%= session.getAttribute("booking_time") %></p>
    <p><strong>Distance:</strong> <%= session.getAttribute("distance") %> km</p>
    <p><strong>Total Fare:</strong> $<%= session.getAttribute("fare") %></p>

    <form action="ConfirmPaymentServlet" method="post">
        <input type="hidden" name="booking_id" value="<%= session.getAttribute("booking_id") %>">

        <label>Payment Method:</label>
        <select name="payment_method">
            <option value="CARD">Credit/Debit Card</option>
            <option value="CASH">Cash</option>
        </select>

        <button type="submit">Confirm Payment</button>
    </form>


</div>
</body>
</html>
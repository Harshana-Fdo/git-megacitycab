<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/9/2025
  Time: 10:27 PM
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
<%


    List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Your Booking History</title>
    <link rel="stylesheet" type="text/css" href="css/booking_history.css">
</head>
<body>

<div class="container">
    <h2>📋 Your Booking History</h2>

    <table>
        <thead>
        <tr>
            <th>Booking ID</th>
            <th>Pickup Location</th>
            <th>Dropoff Location</th>
            <th>Distance (km)</th>
            <th>Fare ($)</th>
            <th>Status</th>
            <th>Booking Time</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <% if (bookings == null || bookings.isEmpty()) { %>
        <tr>
            <td colspan="8" class="no-bookings">🚫 No bookings found.</td>
        </tr>
        <% } else {
            for (Booking b : bookings) { %>
        <tr>
            <td><%= b.getBookingId() %></td>
            <td><%= b.getPickupLocation() %></td>
            <td><%= b.getDropoffLocation() %></td>
            <td><%= b.getDistance() %></td>
            <td>$<%= b.getFare() %></td>
            <td class="<%= "PAID".equals(b.getStatus()) ? "status-paid" : "status-pending" %>">
                <%= b.getStatus() %>
            </td>
            <td><%= b.getBookingTime() %></td>
            <td>
                <% if ("PENDING".equals(b.getStatus())) { %>
                <form action="PaymentServlet" method="post">
                    <input type="hidden" name="booking_id" value="<%= b.getBookingId() %>">
                    <button type="submit" class="pay-btn">💳 Pay Now</button>
                </form>
                <% } else { %>
                <button class="paid-btn" disabled>✅ Paid</button>
                <% } %>
            </td>
        </tr>
        <% } } %>
        </tbody>
    </table>

    <form action="customer_dashboard.jsp">
        <button type="submit" class="dashboard">← Back to Dashboard</button>
    </form>
</div>

</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/9/2025
  Time: 10:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, com.example.megacitycab.dao.DatabaseConnection" %>
<%@ page import="com.example.megacitycab.model.Vehicle" %>
<%@ page session="true" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.megacitycab.model.Booking" %>
<%@ page import="com.example.megacitycab.model.User" %>

<%
    User loggedUser = (User) session.getAttribute("user");
    if (String.valueOf(loggedUser.getUserId()) == null || !"CUSTOMER".equals(loggedUser.getRole())) {
        response.sendRedirect("login.jsp?error=unauthorized_access");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Book a Ride</title>
    <link rel="stylesheet" type="text/css" href="css/booking.css">
</head>
<body>
<div class="container">
    <h2>🚖 Book a Ride</h2>

    <form action="BookingServlet" method="post">
        <label>📍 Pickup Location:</label>
        <input type="text" name="pickup_location" required>

        <label>📍 Dropoff Location:</label>
        <input type="text" name="dropoff_location" required>

        <label>🚗 Select Vehicle:</label>
        <select name="vehicle_id" required>
            <% List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles");
                if (vehicles != null && !vehicles.isEmpty()) {
                    for (Vehicle v : vehicles) { %>
            <option value="<%= v.getVehicleId() %>"><%= v.getModel() %> - <%= v.getVehicleNumber() %></option>
            <%   } } else { %>
            <option value="">No available vehicles</option>
            <% } %>
        </select>

        <label>📏 Distance (km):</label>
        <input type="number" name="distance" step="0.1" required>

        <button type="submit" class="confirm-btn">Confirm Booking</button>
    </form>

    <form action="customer_dashboard.jsp">
        <button type="submit" class="back-btn">⬅ Back to Dashboard</button>
    </form>
</div>
</body>
</html>
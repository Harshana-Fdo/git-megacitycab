<%@ page import="com.example.megacitycab.model.User" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/9/2025
  Time: 10:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%
    User loggedUser = (User) session.getAttribute("user");
    if (String.valueOf(loggedUser.getUserId()) == null || !"CUSTOMER".equals(loggedUser.getRole())) {
        response.sendRedirect("login.jsp?error=unauthorized_access");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/customer_dashboard.css">
</head>
<body>
<div class="container">
    <!-- Left Panel -->
    <div class="left-panel">
        <h1>Welcome to Mega City Cab</h1>
        <p>Book your ride, check your booking history, and manage your account with ease.</p>
    </div>

    <!-- Right Panel (Dashboard Actions) -->
    <div class="right-panel">
        <h2>Customer Dashboard</h2>
        <p>Welcome, <%= ((User) session.getAttribute("user")).getUsername() %>!</p>

        <!-- Forms for Dashboard Actions -->
        <form action="BookingServlet" method="get">
            <button type="submit" class="dashboard-btn">🚖 Book a Ride</button>
        </form>

        <form action="CustomerBookingServlet" method="get">
            <button type="submit" class="dashboard-btn">📋 View Booking History</button>
        </form>

        <form action="help.jsp" method="get">
            <button type="submit" class="dashboard-btn">🆘 Help & Guide</button>
        </form>

        <form action="LogoutServlet" method="post">
            <button type="submit" class="dashboard-btn logout-btn">🚪 Logout</button>
        </form>
    </div>
</div>
</body>
</html>
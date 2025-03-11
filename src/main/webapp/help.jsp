<%@ page import="com.example.megacitycab.model.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User loggedUser = (User) session.getAttribute("user");
    if (String.valueOf(loggedUser.getUserId()) == null || !"CUSTOMER".equals(loggedUser.getRole())) {
        response.sendRedirect("login.jsp?error=unauthorized_access");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Help & User Guide</title>
    <link rel="stylesheet" type="text/css" href="css/help.css">
</head>
<body>
<div class="container">
    <!-- Left Panel (Information Section) -->
    <div class="left-panel">
        <h1>Need Help?</h1>
        <p>Find answers to common questions and understand how to use the system efficiently.</p>
    </div>

    <!-- Right Panel (Help Content) -->
    <div class="right-panel">
        <h2>User Guide</h2>
        <ul>
            <li><strong>🚖 Booking a Ride:</strong> Click the "Book a Ride" button on the dashboard and fill in the details.</li>
            <li><strong>📋 View Booking History:</strong> Track all your past and current bookings in the "Booking History" section.</li>
            <li><strong>💳 Making a Payment:</strong> Select a booking, choose your payment method, and confirm your payment.</li>
            <li><strong>📞 Customer Support:</strong> Contact our support team at <a href="mailto:support@megacitycab.com">support@megacitycab.com</a></li>
        </ul>

        <form action="customer_dashboard.jsp" method="get">
            <button type="submit" class="back-btn">⬅ Back to Dashboard</button>
        </form>
    </div>
</div>
</body>
</html>
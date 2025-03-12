<%@ page import="com.example.megacitycab.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%
    User loggedUser = (User) session.getAttribute("user");
    if (loggedUser == null || !"ADMIN".equals(loggedUser.getRole())) {
        response.sendRedirect("login.jsp?error=unauthorized_access");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/admin_dashboard.css">
</head>
<body>
<div class="dashboard-container">
    <h2>Welcome, Admin <%= loggedUser.getUsername() %>!</h2>

    <div class="dashboard-buttons">
        <form action="AdminVehicleServlet" method="get">
            <button type="submit" class="manage-vehicles">🚗 Manage Vehicles</button>
        </form>

        <form action="AdminBookingServlet" method="get">
            <button type="submit" class="manage-bookings">📋 Manage Bookings</button>
        </form>

        <form action="AdminPaymentServlet" method="get">
            <button type="submit" class="view-reports">📊 View Reports</button>
        </form>

        <form action="AdminUserServlet" method="get">
            <button type="submit" class="manage-users">👥 Manage Users</button>
        </form>

        <!-- ✅ Add Register Admin Button -->
        <form action="admin_registration.jsp" method="get">
            <button type="submit" class="register-admin">👤 Register Admin</button>
        </form>

        <form action="LogoutServlet" method="post">
            <button type="submit" class="logout-button">🚪 Logout</button>
        </form>
    </div>
</div>
</body>
</html>
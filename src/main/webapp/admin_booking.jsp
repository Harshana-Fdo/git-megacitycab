
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.example.megacitycab.model.Booking"
%>
<%@ page session="true" %>
<%@page import="com.example.megacitycab.dao.DatabaseConnection" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.megacitycab.model.User" %>
<%
    User loggedUser = (User) session.getAttribute("user");
    if (String.valueOf(loggedUser.getUserId()) == null || !"ADMIN".equals(loggedUser.getRole())) {
        response.sendRedirect("login.jsp?error=unauthorized_access");
    }
%>

<%
    ArrayList<Booking> bookings = (ArrayList<Booking>) request.getAttribute("bookings");
%>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/admin_booking.css">
    <title>Admin - Booking Management</title>
</head>
<body>
<h2>Booking Management</h2>



<table border="1">
    <tr>
        <th>Booking ID</th><th>User ID</th><th>Pickup</th><th>Dropoff</th><th>Distance (km)</th>
        <th>Fare</th><th>Status</th><th>Actions</th>
    </tr>
    <%
        if(bookings == null){
    %>
    <tr><td colspan="8" style="color: red; text-align: center;">No bookings found in the system.</td></tr>
    <%
    }else{
        for (int i = 0; i < bookings.size(); i++) {

    %>
    <tr>
        <td><%= bookings.get(i).getBookingId() %></td>
        <td><%= bookings.get(i).getUserId() %></td>
        <td><%= bookings.get(i).getPickupLocation() %></td>
        <td><%= bookings.get(i).getDropoffLocation() %></td>
        <td><%= bookings.get(i).getDistance() %></td>
        <td><%= bookings.get(i).getFare() %></td>
        <td><%= bookings.get(i).getStatus() %></td>
        <td>
            <form action="AdminBookingServlet" method="post">
                <input type="hidden" name="booking_id" value="<%= bookings.get(i).getBookingId() %>">
                <input type="hidden" name="action" value="update">
                <select name="status">
                    <option value="PENDING" <%= bookings.get(i).getStatus().equals("PENDING") ? "selected" : "" %>>Pending</option>
                    <option value="CONFIRMED" <%= bookings.get(i).getStatus().equals("CONFIRMED") ? "selected" : "" %>>Confirmed</option>
                    <option value="CANCELED" <%= bookings.get(i).getStatus().equals("CANCELED") ? "selected" : "" %>>Canceled</option>
                </select>
                <button type="submit">Update</button>
            </form>
        </td>
    </tr>
    <%
            }}
    %>

</table>
<form action="admin_dashboard.jsp" method="get">
    <button type="submit" class="back-dashboard">⬅️ Back to Dashboard</button>
</form>
</body>
</html>
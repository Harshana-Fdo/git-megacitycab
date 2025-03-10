
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, com.example.megacitycab.dao.DatabaseConnection" %>
<%@ page import="com.example.megacitycab.model.User" %>
<%@ page session="true" %>

<%
    User loggedUser = (User) session.getAttribute("user");
    if (String.valueOf(loggedUser.getUserId()) == null || !"ADMIN".equals(loggedUser.getRole())) {
        response.sendRedirect("login.jsp?error=unauthorized_access");
    }
%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/admin_payment.css">
    <title>Admin - Payment & Billing Reports</title>
</head>
<body>
<h2>Payment & Billing Reports</h2>

<form action="AdminPaymentServlet" method="get">
    <label>Start Date:</label>
    <input type="date" name="startDate" required>
    <label>End Date:</label>
    <input type="date" name="endDate" required>
    <button type="submit">Filter</button>
</form>

<h3>All Payments</h3>
<table border="1">
    <tr>
        <th>Payment ID</th><th>Booking ID</th><th>Amount</th><th>Payment Method</th><th>Payment Time</th>
    </tr>
    <%
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM payments ORDER BY payment_time DESC");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
    %>
    <tr>
        <td><%= rs.getInt("payment_id") %></td>
        <td><%= rs.getInt("booking_id") %></td>
        <td>$<%= rs.getDouble("amount") %></td>
        <td><%= rs.getString("payment_method") %></td>
        <td><%= rs.getTimestamp("payment_time") %></td>
    </tr>
    <%
        }
    %>
</table>

<h3>Total Earnings: $<%= request.getAttribute("totalEarnings") != null ? request.getAttribute("totalEarnings") : "0.00" %></h3></body>
</html>
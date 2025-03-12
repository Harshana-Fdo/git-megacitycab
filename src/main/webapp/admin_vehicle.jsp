
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.example.megacitycab.model.Vehicle" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.*, com.example.megacitycab.dao.DatabaseConnection" %>
<%@ page import="com.example.megacitycab.model.User" %>
<%@ page session="true" %>


<%

    User loggedUser = (User) session.getAttribute("user");
    if (String.valueOf(loggedUser.getUserId()) == null || !"ADMIN".equals(loggedUser.getRole())) {
        response.sendRedirect("login.jsp?error=unauthorized_access");
    }
%>
<%
    List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles");
%>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/admin_vehicle.css">
    <title>Admin - Vehicle Management</title>
    <script>
        function openUpdateForm(vehicleId, driverName, model, status) {
            document.getElementById("update_vehicle_id").value = vehicleId;
            document.getElementById("update_driver_name").value = driverName;
            document.getElementById("update_model").value = model;
            document.getElementById("update_status").value = status;
            document.getElementById("updateForm").style.display = "block";
        }

        function closeUpdateForm() {
            document.getElementById("updateForm").style.display = "none";
        }
    </script>
</head>
<body>

<h2>Vehicle Management</h2>

<h3>All Vehicles</h3>
<table border="1">
    <tr><th>ID</th><th>Driver</th><th>Number</th><th>Model</th><th>Status</th><th>Actions</th></tr>
    <%
        if (vehicles == null || vehicles.isEmpty()) {
    %>
    <tr><td colspan="6" style="color: red; text-align: center;">No vehicles found.</td></tr>
    <%
    } else {
        for (Vehicle vehicle : vehicles) {
    %>
    <tr>
        <td><%= vehicle.getVehicleId() %></td>
        <td><%= vehicle.getDriverName() %></td>
        <td><%= vehicle.getVehicleNumber() %></td>
        <td><%= vehicle.getModel() %></td>
        <td><%= vehicle.getStatus() %></td>
        <td>
            <button onclick="openUpdateForm('<%= vehicle.getVehicleId() %>', '<%= vehicle.getDriverName() %>', '<%= vehicle.getModel() %>', '<%= vehicle.getStatus() %>')">Update</button>
            <form action="AdminVehicleServlet" method="post" style="display:inline;">
                <input type="hidden" name="vehicle_id" value="<%= vehicle.getVehicleId() %>">
                <input type="hidden" name="action" value="delete">
                <button type="submit" onclick="return confirm('Are you sure you want to delete this vehicle?');">Delete</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

<!-- 🔹 Update Vehicle Form (Hidden Initially) -->
<div id="updateForm" style="display:none; border: 1px solid black; padding: 10px; margin-top: 20px;">
    <h3>Update Vehicle</h3>
    <form action="AdminVehicleServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="vehicle_id" id="update_vehicle_id">
        <label>Driver Name:</label><input type="text" name="driver_name" id="update_driver_name" required><br>
        <label>Model:</label><input type="text" name="model" id="update_model" required><br>
        <label>Status:</label>
        <select name="status" id="update_status">
            <option value="AVAILABLE">Available</option>
            <option value="BOOKED">Booked</option>
            <option value="MAINTENANCE">Maintenance</option>
        </select><br>
        <button type="submit">Update Vehicle</button>
        <button type="button" onclick="closeUpdateForm()">Cancel</button>
    </form>
</div>

<h3>Add New Vehicle</h3>
<form action="AdminVehicleServlet" method="post">
    <input type="hidden" name="action" value="add">
    <label>Driver Name:</label><input type="text" name="driver_name" required><br>
    <label>Vehicle Number:</label><input type="text" name="vehicle_number" required><br>
    <label>Model:</label><input type="text" name="model" required><br>
    <label>Status:</label>
    <select name="status">
        <option value="AVAILABLE">Available</option>
        <option value="BOOKED">Booked</option>
        <option value="MAINTENANCE">Maintenance</option>
    </select><br>
    <button type="submit">Add Vehicle</button>
</form>
<form action="admin_dashboard.jsp" method="get">
    <button type="submit" class="back-dashboard">⬅️ Back to Dashboard</button>
</form>
</body>
</html>
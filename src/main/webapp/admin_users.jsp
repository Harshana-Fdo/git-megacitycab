
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, com.example.megacitycab.dao.DatabaseConnection" %>
<%@ page import="com.example.megacitycab.model.User" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>

<%
    User loggedUser = (User) session.getAttribute("user");
    if (String.valueOf(loggedUser.getUserId()) == null || !"ADMIN".equals(loggedUser.getRole())) {
        response.sendRedirect("login.jsp?error=unauthorized_access");
    }
%>
<%
    List<User> users = (List<User>) request.getAttribute("users");
%>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/admin_users.css">
    <title>Admin - User Management</title>
</head>
<body>
<h2>User Management</h2>

<table border="1">
    <tr>
        <th>User ID</th><th>Username</th><th>Email</th><th>Phone</th><th>Full Name</th><th>Status</th><th>Actions</th>
    </tr>
    <%
        if (users == null || users.isEmpty()) {
    %>
    <tr><td colspan="7" style="color: red; text-align: center;">No users found.</td></tr>
    <%
    } else {
        for (User user : users) {
    %>
    <tr>
        <td><%= user.getUserId() %></td>
        <td><%= user.getUsername() %></td>
        <td><%= user.getEmail() %></td>
        <td><%= user.getPhone() %></td>
        <td><%= user.getFullName() %></td>
        <td><%= user.getStatus() %></td>
        <td>
            <!-- Update Form -->
            <form action="AdminUserServlet" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="user_id" value="<%= user.getUserId() %>">
                <input type="text" name="username" value="<%= user.getUsername() %>" required>
                <input type="email" name="email" value="<%= user.getEmail() %>" required>
                <input type="text" name="phone" value="<%= user.getPhone() %>" required>
                <button type="submit">Update</button>
            </form>

            <!-- Toggle User Status -->
            <form action="AdminUserServlet" method="post" style="display:inline;">
                <input type="hidden" name="action" value="toggleStatus">
                <input type="hidden" name="user_id" value="<%= user.getUserId() %>">
                <input type="hidden" name="current_status" value="<%= user.getStatus() %>">
                <button type="submit">
                    <%= user.getStatus().equals("ACTIVE") ? "Deactivate" : "Activate" %>
                </button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>
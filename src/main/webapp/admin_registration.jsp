
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Registration</title>
    <link rel="stylesheet" type="text/css" href="css/admin_register.css">
</head>
<body>
<div class="registration-container">
    <h2>Register a New Admin</h2>

    <form action="RegisterServlet" method="post">
        <input type="hidden" name="role" value="ADMIN"> <!-- Hidden role for Admins -->

        <label>Full Name:</label>
        <input type="text" name="full_name" required>

        <label>Username:</label>
        <input type="text" name="username" required>

        <label>Email:</label>
        <input type="email" name="email" required>

        <label>Phone:</label>
        <input type="text" name="phone" required>

        <label>Password:</label>
        <input type="password" name="password" required>

        <button type="submit">REGISTER ADMIN</button>
    </form>

    <form action="admin_dashboard.jsp">
        <button type="submit" class="dashboard">← Back to Dashboard</button>
    </form>
</div>
</body>
</html>
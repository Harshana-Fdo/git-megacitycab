package com.example.megacitycab.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Connection conn = null;

    private DatabaseConnection() { }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure correct MySQL driver
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MegaCityCabDB", "root", ""); // Check DB name & credentials
            } catch (Exception e) {
                e.printStackTrace(); // Print exception to console
            }
        }
        return conn;
    }
}

package com.example.megacitycab.factory;

import com.example.megacitycab.model.User;

public class UserFactory {
    public static User createUser(int userId, String username, String email, String phone, String fullName, String role, String status) {
        if (role.equalsIgnoreCase("CUSTOMER")) {
            return new User(userId, username, email, phone, fullName, "CUSTOMER", status);
        } else if (role.equalsIgnoreCase("ADMIN")) {
            return new User(userId, username, email, phone, fullName, "ADMIN", status);
        }
        return null; // If role is unknown, return null
    }
}

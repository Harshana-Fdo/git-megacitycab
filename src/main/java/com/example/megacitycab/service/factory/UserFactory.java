package com.example.megacitycab.service.factory;

import com.example.megacitycab.model.User;

public class UserFactory {
    public static User createUser(int userId, String username,String password, String email, String phone, String fullName, String role, String status) {
        if (role.equalsIgnoreCase("CUSTOMER") || role.equalsIgnoreCase("ADMIN")) {
            return new User(userId, username, password, email, phone, fullName, role, status);
        }
        return null; // If role is invalid
    }
}

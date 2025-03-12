package com.example.megacitycab.service;

import com.example.megacitycab.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private UserService userService = new UserService();


    @Test
    void updateUserDetails() {
        // Assuming user ID 19 exists in the database
        int userId = 19;
        String username = "UpdatedUser1";
        String email = "updateduser1@gmail.com";
        String phone = "0771234561";

        boolean result = userService.updateUserDetails(userId, username, email, phone);

        // Assert: Update should be successful
        assertTrue(result, "Expected user details update to be successful");
    }

    @Test
    void toggleUserStatus() {

        int userId = 19;
        String currentStatus = "ACTIVE";
        String newStatus = "ACTIVE".equals(currentStatus) ? "INACTIVE" : "ACTIVE";

        boolean result = userService.toggleUserStatus(userId, newStatus);


        assertTrue(result, "Expected user status toggle to be successful");
    }
}
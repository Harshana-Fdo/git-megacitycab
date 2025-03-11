package com.example.megacitycab.service;


import static org.junit.jupiter.api.Assertions.*;

import com.example.megacitycab.dao.UserDAO;
import com.example.megacitycab.factory.UserFactory;
import com.example.megacitycab.model.User;
import org.junit.Test;

public class AuthServiceTest {

    private UserDAO userDAO = new UserDAO();
    @Test
    public void testValidLogin() {


       AuthService authService = new AuthService();

       assertNotEquals(null, authService.login("rasindu","rrrr"));

    }

    @Test
    public void testInvalidLogin() {
        AuthService authService = new AuthService();
        assertNull(authService.login("wrongUser", "wrongPass"));
    }
    @Test
    public void testValidCustomerRegistration() {
        AuthService authService = new AuthService();
        User customer = UserFactory.createUser(0, "test_customer", "password123",
                "testcustomer@example.com", "123456789", "Test Customer", "CUSTOMER", "ACTIVE");

        boolean result = authService.registerUser(customer);
        assertTrue(result, "Expected customer registration to succeed");
    }


    @Test
    public void testValidAdminRegistration() {
        AuthService authService = new AuthService();
        User admin = UserFactory.createUser(0, "test_admin", "adminpass",
                "testadmin@example.com", "987654321", "Test Admin", "ADMIN", "ACTIVE");

        boolean result = authService.registerUser(admin);
        assertTrue(result, "Expected admin registration to succeed");
    }


    @Test
    public void testDuplicateUsernameRegistration() {
        AuthService authService = new AuthService();
        User duplicateUser = UserFactory.createUser(0, "existing_user", "password123",
                "existing@example.com", "123456789", "Existing User", "CUSTOMER", "ACTIVE");

        boolean firstAttempt = authService.registerUser(duplicateUser);
        boolean secondAttempt = authService.registerUser(duplicateUser);

        assertTrue(firstAttempt, "Expected first registration to succeed");
        assertFalse(secondAttempt, "Expected second registration to fail due to duplicate username");
    }



}
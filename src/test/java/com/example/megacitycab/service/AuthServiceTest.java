package com.example.megacitycab.service;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

public class AuthServiceTest {


    @Test
    public void testValidLogin() {


       AuthService authService = new AuthService();

       assertNotEquals(null,
               authService.login("rasindu","rrrr"));

    }
}
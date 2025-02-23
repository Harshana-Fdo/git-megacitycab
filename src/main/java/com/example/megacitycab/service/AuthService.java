package com.example.megacitycab.service;

import com.example.megacitycab.dao.UserDAO;
import com.example.megacitycab.model.User;

public class AuthService {

    private UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    // Handles user login
    public User login(String username, String password) {
        return userDAO.getUserByUsernameAndPassword(username, password);
    }

    // Handles user registration
    public boolean registerUser(User user) {
        return userDAO.registerUser(user);
    }
}

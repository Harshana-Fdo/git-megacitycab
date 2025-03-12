package com.example.megacitycab.service;

import com.example.megacitycab.dao.UserDAO;
import com.example.megacitycab.model.User;

import java.util.List;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean updateUser(int userId, String name, String email, String phone) {
        return userDAO.updateUser(userId, name, email, phone);
    }

    public boolean deactivateUser(int userId) {
        return userDAO.deactivateUser(userId);
    }

    public List<User> getAllCustomers() {
        return userDAO.getAllCustomers();
    }

    public boolean updateUserDetails(int userId, String username, String email, String phone) {
        return userDAO.updateUserDetails(userId, username, email, phone);
    }

    public boolean toggleUserStatus(int userId, String status) {
        return userDAO.toggleUserStatus(userId, status);
    }
}



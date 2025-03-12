package com.example.megacitycab.service;

import com.example.megacitycab.dao.UserDAO;
import com.example.megacitycab.model.User;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {

    private UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    // Handles user login
    public User login(String username, String password) {

        User user = userDAO.getUserbyusername(username);

        if(user!=null){
            if(PasswordUtil.checkPassword(password, user.getPassword())) {
                user.setPassword(null);
                return user;
            }
        }
        return null;

    }



    // Handles user registration
    public boolean registerUser(User user) {
        String hashedPassword = PasswordUtil.encryptPassword(user.getPassword());
        user.setPassword(hashedPassword);
        return userDAO.registerUser(user);
    }

}

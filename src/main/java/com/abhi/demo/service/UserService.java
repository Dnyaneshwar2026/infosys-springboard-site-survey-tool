package com.abhi.demo.service;

import com.abhi.demo.dao.UserDAO;
import com.abhi.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;  // ← Use DAO instead of Repository

    public User createUser(User user) {
        if (userDAO.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (userDAO.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new RuntimeException("Phone number already exists");
        }
        return userDAO.save(user);
    }




}

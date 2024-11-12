package com.controller;

import com.entity.User;
import com.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // User registration
    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        userService.registerUser(username, email, password);
        return "User registered successfully!";
    }

    // User login
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        boolean loginSuccess = userService.authenticateUser(username, password);
        if (loginSuccess) {
            return "Login successful!";
        } else {
            return "Invalid credentials!";
        }
    }

    // Get user by ID
    @GetMapping("/profile/{userId}")
    public User getUserProfile(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }
}

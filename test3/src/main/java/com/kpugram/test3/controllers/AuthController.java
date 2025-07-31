package com.kpugram.test3.controllers;

import com.kpugram.test3.dto.UserDTO;
import com.kpugram.test3.models.User;
import com.kpugram.test3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * AuthController handles user registration and login endpoints.
 * Routes:
 *  - POST /api/auth/register
 *  - POST /api/auth/login
 *
 *
 */

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Register endpoint
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        Optional<User> existingUser = userService.login(user.getEmail(), user.getPassword());
        if (existingUser.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("User already exists with this email");
        }

        UserDTO registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        Optional<User> user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (user.isPresent()) {
            return ResponseEntity.ok(userService.convertToDTO(user.get()));
        } else {
            return ResponseEntity
                    .status(401)
                    .body("Invalid email or password");
        }
    }
}

/*
this is the manual while testing for this AuthController.java

Register User:
    Endpoint: POST /api/auth/register
    Body (JSON -> raw ->application.json):
        {
            "name" : "username",
            "email": "userEmail",
            "password": "userPassword_Creating",
            "profilePicture": "https://example.com/profile.jpg"
        }

        // this will create the new user account in the database

Login User:
    Endpoint: POST /api/auth/login
    Body (JSON ):
        {
            "email": "userEmail_Registered",
            "password": "userPassword"
        }

        // this will help the user to login it in to the application and go to the Home page of
        // theit KPUGRAM+


* */


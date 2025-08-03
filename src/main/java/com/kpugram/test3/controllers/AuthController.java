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
 * It defines routes for creating a new user and validating login credentials.
 *
 * Base Route:
 *   /api/auth
 *
 * Endpoints:
 *   - POST /register : Registers a new user
 *   - POST /login    : Authenticates existing users
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * Registers a new user in the system.
     * Before creating the user, it checks if an account with the same email already exists.
     *
     * @param user The user object containing registration details
     * @return HTTP response indicating success or failure with relevant message or UserDTO
     */
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

    /**
     * Authenticates a user using the provided email and password.
     * Returns a UserDTO on successful login, or an error response if credentials are invalid.
     *
     * @param loginRequest The login request object containing email and password
     * @return HTTP response containing UserDTO or error message
     */
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
Manual Testing Guide for AuthController:

1. Register User:
   - Endpoint: POST /api/auth/register
   - Body (application/json):
     {
         "name" : "username",
         "email": "userEmail",
         "password": "userPassword_Creating",
         "profilePicture": "https://example.com/profile.jpg"
     }
   - Description: Creates a new user and stores the record in the database.

2. Login User:
   - Endpoint: POST /api/auth/login
   - Body (application/json):
     {
         "email": "userEmail_Registered",
         "password": "userPassword"
     }
   - Description: Validates user credentials and returns user information upon successful login.
*/

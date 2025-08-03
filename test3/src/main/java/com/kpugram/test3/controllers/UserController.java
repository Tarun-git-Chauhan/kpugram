package com.kpugram.test3.controllers;

import com.kpugram.test3.dto.UserProfileDTO;
import com.kpugram.test3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * UserController handles general user-related operations.
 * In this version, it provides access to user profile information.
 *
 * Base Route:
 *   /api/user
 *
 * Endpoint:
 *   - GET /profile/{userId} : Retrieve a user's profile and posts
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves the profile of a specific user by their ID.
     * The response includes both user information and their post history.
     *
     * @param userId The ID of the user to fetch
     * @return UserProfileDTO containing user data and posts
     */
    @GetMapping("/profile/{userId}")
    public UserProfileDTO getProfile(@PathVariable Integer userId) {
        return userService.getUserProfile(userId);
    }
}

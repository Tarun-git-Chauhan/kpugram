package com.kpugram.test3.controllers;

import com.kpugram.test3.dto.UserProfileDTO;
import com.kpugram.test3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ProfileController provides endpoints related to user profile data.
 * This includes retrieving user details along with their created posts.
 *
 * Base Route:
 *   /api/profile
 *
 * Endpoint:
 *   - GET /{userId} : Retrieve a user's profile and associated posts
 */
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves a user's profile using their ID.
     * The profile includes basic user information and a list of their posts.
     *
     * @param userId The ID of the user whose profile is being requested
     * @return UserProfileDTO containing profile data and associated posts
     */
    @GetMapping("/{userId}")
    public UserProfileDTO getUserProfile(@PathVariable Integer userId) {
        return userService.getUserProfile(userId);
    }
}

package com.kpugram.test3.controllers;

import com.kpugram.test3.dto.UserProfileDTO;
import com.kpugram.test3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    // GET full user profile with their posts
    @GetMapping("/{userId}")
    public UserProfileDTO getUserProfile(@PathVariable Integer userId) {
        return userService.getUserProfile(userId);
    }
}

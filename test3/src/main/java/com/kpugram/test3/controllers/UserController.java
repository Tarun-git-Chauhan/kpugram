package com.kpugram.test3.controllers;

import com.kpugram.test3.dto.UserProfileDTO;
import com.kpugram.test3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // GET /api/user/profile/5
    @GetMapping("/profile/{userId}")
    public UserProfileDTO getProfile(@PathVariable Integer userId) {
        return userService.getUserProfile(userId);
    }
}

package com.kpugram.test3.controllers;

import com.kpugram.test3.repositories.UserRepository;
import com.kpugram.test3.services.CommentService;
import com.kpugram.test3.services.PostService;
import com.kpugram.test3.services.UserService;
import com.kpugram.test3.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private PostService postService;

    // Admin deletes any post
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<String> deletePostAsAdmin(@PathVariable Integer postId, @RequestParam Integer adminId) {
        String result = postService.deletePost(postId, adminId);
        return ResponseEntity.ok(result);
    }
    @Autowired
    private UserRepository userRepository;
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }
    // More admin features can be added here
}

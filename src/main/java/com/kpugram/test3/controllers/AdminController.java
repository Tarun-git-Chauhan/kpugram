package com.kpugram.test3.controllers;

import com.kpugram.test3.dto.UserDTO;
import com.kpugram.test3.models.Post;
import com.kpugram.test3.models.User;
import com.kpugram.test3.repositories.PostRepository;
import com.kpugram.test3.repositories.UserRepository;
import com.kpugram.test3.services.CommentService;
import com.kpugram.test3.services.PostService;
import com.kpugram.test3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * AdminController provides administrative-level actions for managing users and posts.
 * All routes in this controller are intended to be accessed only by users with administrative privileges.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    /**
     * Retrieve all registered users in the system.
     * This endpoint returns a list of UserDTO objects containing non-sensitive user data.
     *
     * @return HTTP response with a list of users
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = users.stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }

    /**
     * Deletes a user identified by their unique ID.
     * Can be used to enforce moderation or handle account removal by admins.
     *
     * @param userId the ID of the user to be deleted
     * @return HTTP response with success or failure message
     */
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(404).body("User not found");
        }

        // Associated posts or comments may be cleaned manually via service if required
        userRepository.deleteById(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    /**
     * Deletes a post identified by its unique ID.
     * Allows administrators to remove content that violates platform rules.
     *
     * @param postId the ID of the post to be deleted
     * @return HTTP response with success or failure message
     */
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable int postId) {
        if (!postRepository.existsById(postId)) {
            return ResponseEntity.status(404).body("Post not found");
        }

        postRepository.deleteById(postId);
        return ResponseEntity.ok("Post deleted successfully");
    }

    /**
     * Promotes a regular user to administrator status by updating their isAdmin flag.
     * Can only be done by an existing admin.
     *
     * @param userId the ID of the user to be promoted
     * @return HTTP response with confirmation or error message
     */
    @PutMapping("/promote/{userId}")
    public ResponseEntity<String> promoteUserToAdmin(@PathVariable int userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        if (user.isAdmin()) {
            return ResponseEntity.badRequest().body("User is already an admin");
        }

        user.setAdmin(true);
        userRepository.save(user);
        return ResponseEntity.ok("User promoted to admin");
    }
}

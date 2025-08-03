package com.kpugram.test3.controllers;

import com.kpugram.test3.dto.LikeDTO;
import com.kpugram.test3.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * LikeController handles all endpoints related to liking posts.
 * Includes actions to like a post, fetch users who liked a post, and get the like count.
 *
 * Base Route:
 *   /api/likes
 *
 * Endpoints:
 *   - POST /like         : User likes a post
 *   - GET /post/{postId} : Get list of users who liked a post
 *   - GET /count/{postId}: Get total like count for a post
 */
@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    /**
     * Allows a user to like a post.
     * If the user has already liked the post, the service may return a message indicating that.
     *
     * @param userId The ID of the user performing the like action
     * @param postId The ID of the post being liked
     * @return A message indicating success or status of the like action
     */
    @PostMapping("/like")
    public String likePost(@RequestParam Integer userId, @RequestParam Integer postId) {
        return likeService.likePost(userId, postId);
    }

    /**
     * Retrieves a list of all users who liked a particular post.
     * Each like is represented as a LikeDTO with user information.
     *
     * @param postId The ID of the post
     * @return List of LikeDTOs representing the users who liked the post
     */
    @GetMapping("/post/{postId}")
    public List<LikeDTO> getLikes(@PathVariable Integer postId) {
        return likeService.getLikesForPost(postId);
    }

    /**
     * Returns the total number of likes for a given post.
     *
     * @param postId The ID of the post
     * @return The total like count as a Long
     */
    @GetMapping("/count/{postId}")
    public Long getLikeCount(@PathVariable Integer postId) {
        return likeService.getLikeCount(postId);
    }
}

/*
Manual Testing Guide for LikeController:

1. Like a Post:
   - Endpoint: POST /api/likes/like?userId=1&postId=3
   - Description: User with ID 1 likes the post with ID 3. Returns status message.

2. Get Users Who Liked a Post:
   - Endpoint: GET /api/likes/post/3
   - Description: Returns a list of users who liked post ID 3 as DTOs.

3. Get Like Count for a Post:
   - Endpoint: GET /api/likes/count/3
   - Description: Returns total number of likes on post ID 3.
*/

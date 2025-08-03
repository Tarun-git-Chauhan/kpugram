package com.kpugram.test3.controllers;

import com.kpugram.test3.dto.CommentDTO;
import com.kpugram.test3.dto.CommentRequest;
import com.kpugram.test3.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CommentController handles operations related to creating and retrieving comments.
 *
 * Base Route:
 *   /api/comments
 *
 * Endpoints:
 *   - POST /add           : Adds a new comment to a post
 *   - GET /post/{postId}  : Retrieves all comments for a given post
 */
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * Adds a new comment to the specified post.
     * The request body should contain postId, userId, and content.
     *
     * @param request The comment request containing post ID, user ID, and comment content
     * @return CommentDTO representing the created comment
     */
    @PostMapping("/add")
    public CommentDTO addComment(@RequestBody CommentRequest request) {
        return commentService.addComment(request.getPostId(), request.getUserId(), request.getContent());
    }

    /**
     * Retrieves all comments associated with a given post.
     *
     * @param postId The ID of the post
     * @return List of CommentDTOs related to the specified post
     */
    @GetMapping("/post/{postId}")
    public List<CommentDTO> getComments(@PathVariable Integer postId) {
        return commentService.getCommentsForPost(postId);
    }
}

/*
Manual Testing Guide for CommentController:

1. Add Comment to a Post:
   - Endpoint: POST /api/comments/add
   - Body (application/json):
     {
         "postId": 1,
         "userId": 1,
         "content": "Nice post!"
     }
   - Description: Adds a comment to the post with ID 1 by the user with ID 1.

2. Get Comments for a Post:
   - Endpoint: GET /api/comments/post/1
   - Description: Retrieves all comments associated with post ID 1.
*/

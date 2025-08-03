package com.kpugram.test3.controllers;

import com.kpugram.test3.dto.FeedDTO;
import com.kpugram.test3.dto.PostDTO;
import com.kpugram.test3.models.Post;
import com.kpugram.test3.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * PostController handles creation, retrieval, update, and deletion of user posts.
 * It includes support for file uploads and anonymous posting.
 *
 * Base Route:
 *   /api/posts
 *
 * Endpoints:
 *   - POST   /create          : Create a post (supports file upload)
 *   - GET    /feed            : Retrieve all posts for the public feed
 *   - PUT    /{postId}        : Update an existing post
 *   - DELETE /{postId}        : Delete a post by its ID
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * Creates a new post submitted by a user, optionally with an image file.
     * The post can be marked as anonymous based on the request.
     *
     * Content-Type: multipart/form-data
     *
     * @param userId    ID of the user creating the post
     * @param content   The textual content of the post
     * @param file      Optional image file attached to the post
     * @param anonymous Flag indicating whether the post should be anonymous
     * @return HTTP response confirming post creation
     */
    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<String> createPostWithImage(
            @RequestParam("userId") Integer userId,
            @RequestParam("content") String content,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("anonymous") boolean anonymous) {

        postService.createPostWithImage(userId, content, file, anonymous);
        return ResponseEntity.ok("Post created successfully ✅");
    }

    /**
     * Returns a list of all posts to populate the feed.
     * Anonymous posts will not include user details, while normal posts will.
     *
     * @return HTTP response with a list of FeedDTO objects representing posts
     */
    @GetMapping("/feed")
    public ResponseEntity<List<FeedDTO>> getFeed() {
        return ResponseEntity.ok(postService.getFeedPosts());
    }

    /**
     * Updates the content or image of an existing post.
     * The post must belong to the specified user for the update to succeed.
     *
     * @param postId      ID of the post to update
     * @param userId      ID of the user attempting the update
     * @param updatedData Post object containing updated content and/or metadata
     * @return PostDTO with the updated post information
     */
    @PutMapping("/{postId}")
    public PostDTO updatePost(
            @PathVariable Integer postId,
            @RequestParam Integer userId,
            @RequestBody Post updatedData) {
        return postService.updatePost(postId, userId, updatedData);
    }

    /**
     * Deletes a post by its ID.
     * The post will only be deleted if the specified user is the owner.
     *
     * @param postId ID of the post to delete
     * @param userId ID of the user requesting the deletion
     * @return HTTP response confirming deletion or providing an error message
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postId, @RequestParam Integer userId) {
        String result = postService.deletePost(postId, userId);
        return ResponseEntity.ok(result);
    }
}

/*
Manual Testing Guide for PostController:

1. Create Post - Normal:
   - Endpoint: POST /api/posts/create?userId=1
   - Content-Type: multipart/form-data
   - Fields:
     - content: "This is the content like a caption or some text"
     - file: (Optional image file)
     - anonymous: false

   - Description: Creates a visible post associated with the user.

2. Create Anonymous Post:
   - Endpoint: POST /api/posts/create?userId=1
   - Content-Type: multipart/form-data
   - Fields:
     - content: "This is a secret caption"
     - file: (Optional image file)
     - anonymous: true

   - Description: Creates an anonymous post where username and profile picture are hidden.

3. Get Post Feed:
   - Endpoint: GET /api/posts/feed
   - Description: Returns a list of mixed posts (both anonymous and normal), for display in the frontend feed.

   Example response:
   [
       {
           "id": 2,
           "content": "This is a secret confession.",
           "imageUrl": null,
           "anonymous": true,
           "createdAt": "2025-07-07T21:03:12.173677",
           "username": null,
           "profilePicture": null
       },
       {
           "id": 3,
           "content": "This is a not secret confession.",
           "imageUrl": null,
           "anonymous": false,
           "createdAt": "2025-07-07T21:03:12.173677",
           "username": "someUser",
           "profilePicture": "https://example.com/image.jpg"
       }
   ]
*/

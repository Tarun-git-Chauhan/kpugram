package com.kpugram.test3.controllers;

import com.kpugram.test3.dto.PostDTO;
import com.kpugram.test3.models.Post;
import com.kpugram.test3.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ConfessionController handles operations specific to anonymous posts,
 * often referred to as "confessions" in the context of the platform.
 *
 * Base Route:
 *   /api/confessions
 *
 * Endpoints:
 *   - GET /             : Retrieves all anonymous posts
 *   - POST /create      : Submits a new anonymous confession
 */
@RestController
@RequestMapping("/api/confessions")
public class ConfessionController {

    @Autowired
    private PostService postService;

    /**
     * Retrieves all posts that have been marked as anonymous (i.e., confessions).
     * Omits user-related data such as username and profile picture to ensure anonymity.
     *
     * @return List of PostDTOs with user data nullified
     */
    @GetMapping
    public List<PostDTO> getAllConfessions() {
        return postService.getAllPosts().stream()
                .filter(Post::isAnonymous)
                .map(post -> PostDTO.builder()
                        .id(post.getId())
                        .content(post.getContent())
                        .imageUrl(post.getImageUrl())
                        .anonymous(true)
                        .createdAt(post.getCreatedAt().toString())
                        .username(null)
                        .profilePicture(null)
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * Creates a new anonymous post (confession) on behalf of a given user.
     * Anonymity is enforced regardless of frontend input.
     *
     * @param userId        The ID of the user submitting the confession
     * @param confessionDto The DTO carrying content and image URL
     * @return PostDTO representing the created anonymous post
     */
    @PostMapping(path = "/create", consumes = "application/json")
    public ResponseEntity<PostDTO> createConfession(
            @RequestParam("userId") Integer userId,
            @RequestBody PostDTO confessionDto) {

        // Build a Post entity from the DTO with anonymity enforced
        Post confession = new Post();
        confession.setContent(confessionDto.getContent());
        confession.setImageUrl(confessionDto.getImageUrl());
        confession.setAnonymous(true);

        // Create and return the saved confession
        PostDTO savedDto = postService.createPost(confession, userId);
        return ResponseEntity.ok(savedDto);
    }
}

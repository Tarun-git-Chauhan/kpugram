package com.kpugram.test3.controllers;

import com.kpugram.test3.dto.PostDTO;
import com.kpugram.test3.models.Post;
import com.kpugram.test3.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/confessions")
public class ConfessionController {

    @Autowired
    private PostService postService;

    // Get all anonymous confessions
    @GetMapping
    public List<PostDTO> getAllConfessions() {
        List<Post> confessions = postService.getAllPosts()
                .stream()
                .filter(Post::isAnonymous)
                .collect(Collectors.toList());

        return confessions.stream().map(post -> PostDTO.builder()
                .id(post.getId())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .anonymous(true)
                .createdAt(post.getCreatedAt().toString())
                .username(null)
                .profilePicture(null)
                .build()).toList();
    }
}

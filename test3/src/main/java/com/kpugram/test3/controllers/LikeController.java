package com.kpugram.test3.controllers;

/*
This is the part where:

Frontend sends the like request

Backend responds with messages, like list, or like count
*/
import com.kpugram.test3.dto.LikeDTO;
import com.kpugram.test3.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    // Endpoint to like a post
    @PostMapping("/like")
    public String likePost(@RequestParam Integer userId, @RequestParam Integer postId) {
        return likeService.likePost(userId, postId);
    }

    // Get all users who liked a post
    @GetMapping("/post/{postId}")
    public List<LikeDTO> getLikes(@PathVariable Integer postId) {
        return likeService.getLikesForPost(postId);
    }

    // Get total like count for a post
    @GetMapping("/count/{postId}")
    public Long getLikeCount(@PathVariable Integer postId) {
        return likeService.getLikeCount(postId);
    }
}


/*
POST /api/likes/like?userId=1&postId=3	User likes a post. If already liked, gets message.
GET /api/likes/post/3	Returns all users (DTOs) who liked post ID 3
GET /api/likes/count/3	Returns like count number (e.g. 5) for post ID 3

* */
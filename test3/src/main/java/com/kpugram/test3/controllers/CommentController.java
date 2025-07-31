package com.kpugram.test3.controllers;


import com.kpugram.test3.dto.CommentDTO;
import com.kpugram.test3.dto.CommentRequest;
import com.kpugram.test3.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

//    @PostMapping("/add")
    // here what we need in the json or from front end like post id userid and content
    /*public CommentDTO addComment(@RequestParam Integer postId,
                                 @RequestParam Integer userId,
                                 @RequestParam String content) {
        return commentService.addComment(postId, userId, content);
    }*/
    @PostMapping("/add")
    public CommentDTO addComment(@RequestBody CommentRequest request) {
        return commentService.addComment(request.getPostId(), request.getUserId(), request.getContent());
    }


    @GetMapping("/post/{postId}")
    public List<CommentDTO> getComments(@PathVariable Integer postId) {
        return commentService.getCommentsForPost(postId);
    }
}

/*
this is the manual while testing for CommentController

Add Comment to a Post
    Endpoint: POST /api/comments/add?postId=1&userId=1&content=Nice%20post!

    // Attaches a new comment to the post with ID 1 by user ID 1.
    //
    //Note:
    //
    //You pass values in URL (query params) like:
    //?postId=1&userId=1&content=Nice post!


Get Comments for a post
    Endpoint: GET /api/comments/post/1
* */

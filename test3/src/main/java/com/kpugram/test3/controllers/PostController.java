/*
package com.kpugram.test3.controllers;

import com.kpugram.test3.dto.FeedDTO;
import com.kpugram.test3.dto.PostDTO;
import com.kpugram.test3.models.Post;
import com.kpugram.test3.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

//    @PostMapping("/create/{userId}")
//    public PostDTO createPost(@RequestBody Post post, @PathVariable Integer userId) {
//        return postService.createPost(post, userId);
//    }

    // to accept the form-format not only the json
    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<String> createPostWithImage(
            @RequestParam("userId") Integer userId,
            @RequestParam("content") String content,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("anonymous") boolean anonymous) {

        postService.createPostWithImage(userId, content, file, anonymous);
        return ResponseEntity.ok("Post created successfully ✅");
    }


    // for the feed
    */
/*This is the actual API endpoint.

When frontend calls /api/posts/feed, this method is triggered.

It returns the list of all posts — with or without user info based on the flag.
    * *//*

  */
/*  @GetMapping("/feed")
    public ResponseEntity<List<FeedDTO>> getFeed(Pageable pageable){
        return ResponseEntity.ok(postService.getFeedPosts(pageable));
    }*//*


    // for updating the posts
    @PutMapping("/{postId}")
    public PostDTO updatePost(
            @PathVariable Integer postId,
            @RequestParam Integer userId,
            @RequestBody Post updatedData) {
        return postService.updatePost(postId, userId, updatedData);
    }

    // to delete the posts
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postId, @RequestParam Integer userId) {
        String result = postService.deletePost(postId, userId);
        return ResponseEntity.ok(result);
    }


}

*/
/*
this is the manual while testing for Postcontroller.java

Create Post - Normal
    Endpoint: POST /api/posts/create?userId=1

    Body (JSON):
        {
            "content": "This is the content like a caption or some text",
            "ImageUrl": "https://example.com/image.jpg",
            "anonymous": false
        }

        // for here this will create the post by the userId = 1, anonymous is false it means it can appear in the regulat feed.


Create Anonymous Post
    Endpoint POST /api/posts/create?userId=1

    Body (JSON):
        {
            "content": "This is the content like a caption or some text",
            "ImageUrl": "https://example.com/image.jpg",
            "anonymous": false
        }

        // for here this will create the post by the userId = 1, anonymous is false it means it can appear in the regulat feed.


Create Anonymous Post
    Endpoint: POST /api/posts/create?userId=1

    Body (JSON):
        {
         "content": "This is the content like a caption or some text",
            "ImageUrl": "https://example.com/image.jpg",
            "anonymous": true
        }

        // This hides username/profile in the feed. Acts like Reddit anonymous post.


Get Post Feed (All posts)
    Endpoint: GET /api/posts/feed

    What will you get:
    [
        {"id": 2,
        "content": "This is a secret confession.",
        "imageUrl": null,
        "anonymous": true,
        "createdAt": "2025-07-07T21:03:12.173677",
        "username": null,
        "profilePicture": null
        },

        {"id": 3,
        "content": "This is a not secret confession.",
        "imageUrl": null,
        "anonymous": false,
        "createdAt": "2025-07-07T21:03:12.173677",
        "username": null,
        "profilePicture": null
        }
    ]

    // Shows the frontend feed like Instagram. Posts are mixed with and without user info.
* *//*


*/



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

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

//    @PostMapping("/create/{userId}")
//    public PostDTO createPost(@RequestBody Post post, @PathVariable Integer userId) {
//        return postService.createPost(post, userId);
//    }

    // to accept the form-format not only the json
    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<String> createPostWithImage(
            @RequestParam("userId") Integer userId,
            @RequestParam("content") String content,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("anonymous") boolean anonymous) {

        postService.createPostWithImage(userId, content, file, anonymous);
        return ResponseEntity.ok("Post created successfully ✅");
    }


    // for the feed
    /*This is the actual API endpoint.

When frontend calls /api/posts/feed, this method is triggered.

It returns the list of all posts — with or without user info based on the flag.
    * */
    @GetMapping("/feed")
    public ResponseEntity<List<FeedDTO>> getFeed() {
        return ResponseEntity.ok(postService.getFeedPosts());
    }

    // for updating the posts
    @PutMapping("/{postId}")
    public PostDTO updatePost(
            @PathVariable Integer postId,
            @RequestParam Integer userId,
            @RequestBody Post updatedData) {
        return postService.updatePost(postId, userId, updatedData);
    }

    // to delete the posts
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postId, @RequestParam Integer userId) {
        String result = postService.deletePost(postId, userId);
        return ResponseEntity.ok(result);
    }


}

/*
this is the manual while testing for Postcontroller.java

Create Post - Normal
    Endpoint: POST /api/posts/create?userId=1

    Body (JSON):
        {
            "content": "This is the content like a caption or some text",
            "ImageUrl": "https://example.com/image.jpg",
            "anonymous": false
        }

        // for here this will create the post by the userId = 1, anonymous is false it means it can appear in the regulat feed.


Create Anonymous Post
    Endpoint POST /api/posts/create?userId=1

    Body (JSON):
        {
            "content": "This is the content like a caption or some text",
            "ImageUrl": "https://example.com/image.jpg",
            "anonymous": false
        }

        // for here this will create the post by the userId = 1, anonymous is false it means it can appear in the regulat feed.


Create Anonymous Post
    Endpoint: POST /api/posts/create?userId=1

    Body (JSON):
        {
         "content": "This is the content like a caption or some text",
            "ImageUrl": "https://example.com/image.jpg",
            "anonymous": true
        }

        // This hides username/profile in the feed. Acts like Reddit anonymous post.


Get Post Feed (All posts)
    Endpoint: GET /api/posts/feed

    What will you get:
    [
        {"id": 2,
        "content": "This is a secret confession.",
        "imageUrl": null,
        "anonymous": true,
        "createdAt": "2025-07-07T21:03:12.173677",
        "username": null,
        "profilePicture": null
        },

        {"id": 3,
        "content": "This is a not secret confession.",
        "imageUrl": null,
        "anonymous": false,
        "createdAt": "2025-07-07T21:03:12.173677",
        "username": null,
        "profilePicture": null
        }
    ]

    // Shows the frontend feed like Instagram. Posts are mixed with and without user info.
* */

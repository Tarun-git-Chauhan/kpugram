package com.kpugram.test3.services;

import com.kpugram.test3.dto.LikeDTO;
import com.kpugram.test3.models.Like;
import com.kpugram.test3.models.Post;
import com.kpugram.test3.models.User;
import com.kpugram.test3.repositories.LikeRepository;
import com.kpugram.test3.repositories.PostRepository;
import com.kpugram.test3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * LikeService manages the business logic for liking posts,
 * retrieving like details, and counting likes.
 */
@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepository postRepo;

    /**
     * Allows a user to like a post if they haven't already.
     *
     * @param userId ID of the user performing the like
     * @param postId ID of the post being liked
     * @return A status message indicating the result
     */
    public String likePost(Integer userId, Integer postId) {
        // Check if already liked
        if (likeRepo.findByUserIdAndPostId(userId, postId).isPresent()) {
            return "You already liked this post!";
        }

        User user = userRepo.findById(userId).orElseThrow();
        Post post = postRepo.findById(postId).orElseThrow();

        Like like = Like.builder()
                .user(user)
                .post(post)
                .build();

        likeRepo.save(like);

        return "Post liked successfully!";
    }

    /**
     * Retrieves a list of all users who liked a given post.
     * Each entry is returned as a LikeDTO with basic user info.
     *
     * @param postId ID of the post
     * @return List of LikeDTOs
     */
    public List<LikeDTO> getLikesForPost(Integer postId) {
        return likeRepo.findByPostId(postId).stream().map(like -> LikeDTO.builder()
                .id(like.getId())
                .username(like.getUser().getName())
                .profilePicture(like.getUser().getProfilePicture())
                .build()
        ).collect(Collectors.toList());
    }

    /**
     * Counts the total number of likes on a post.
     * Useful for displaying a like counter on the UI.
     *
     * @param postId ID of the post
     * @return Total number of likes
     */
    public Long getLikeCount(Integer postId) {
        return likeRepo.countByPostId(postId);
    }
}

/*
Summary of Public Methods:

- likePost()         → Checks if user already liked the post; if not, saves the like
- getLikesForPost()  → Returns a list of LikeDTOs (username and profile picture)
- getLikeCount()     → Returns the total like count for a given post
*/

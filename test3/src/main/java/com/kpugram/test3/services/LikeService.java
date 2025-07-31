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

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepository postRepo;

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

    public List<LikeDTO> getLikesForPost(Integer postId) {
        return likeRepo.findByPostId(postId).stream().map(like -> LikeDTO.builder()
                .id(like.getId())
                .username(like.getUser().getName())
                .profilePicture(like.getUser().getProfilePicture())
                .build()
        ).collect(Collectors.toList());
    }

    public Long getLikeCount(Integer postId) {
        return likeRepo.countByPostId(postId);
    }
}


/*likePost()	    ->Checks if user already liked the post → if not, saves the like
getLikesForPost()	->Returns a list of LikeDTOs with usernames and profile pictures
getLikeCount()	    ->Just counts how many people liked the post (used in UI as a counter)





* */
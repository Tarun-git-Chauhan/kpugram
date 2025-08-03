package com.kpugram.test3.services;

import com.kpugram.test3.dto.CommentDTO;
import com.kpugram.test3.models.Comment;
import com.kpugram.test3.models.Post;
import com.kpugram.test3.models.User;
import com.kpugram.test3.repositories.CommentRepository;
import com.kpugram.test3.repositories.PostRepository;
import com.kpugram.test3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CommentService handles the business logic related to creating and retrieving comments.
 * It connects the controller layer with the data layer by processing comment-related requests.
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;

    /**
     * Adds a new comment to a specified post by a specified user.
     *
     * @param postId  ID of the post to comment on
     * @param userId  ID of the user creating the comment
     * @param content Text content of the comment
     * @return CommentDTO representing the saved comment
     */
    public CommentDTO addComment(Integer postId, Integer userId, String content) {
        User user = userRepo.findById(userId).orElseThrow(null);
        Post post = postRepo.findById(postId).orElseThrow(null);

        Comment comment = Comment.builder()
                .content(content)
                .createdAt(LocalDateTime.now())
                .user(user)
                .post(post)
                .build();
        comment = commentRepo.save(comment);

        return CommentDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt().toString())
                .username(user.getName())
                .profilePicture(user.getProfilePicture())
                .build();
    }

    /**
     * Retrieves all comments for a specific post.
     *
     * @param postId ID of the post whose comments should be fetched
     * @return List of CommentDTOs representing all associated comments
     */
    public List<CommentDTO> getCommentsForPost(Integer postId) {
        List<Comment> comments = commentRepo.findByPostId(postId);

        return comments.stream().map(comment -> CommentDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt().toString())
                .username(comment.getUser().getName())
                .profilePicture(comment.getUser().getProfilePicture())
                .build()
        ).collect(Collectors.toList());
    }
}

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

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;

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

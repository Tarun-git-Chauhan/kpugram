package com.kpugram.test3.services;

import com.kpugram.test3.dto.FeedDTO;
import com.kpugram.test3.dto.PostDTO;
import com.kpugram.test3.models.Post;
import com.kpugram.test3.models.User;
import com.kpugram.test3.repositories.CommentRepository;
import com.kpugram.test3.repositories.PostRepository;
import com.kpugram.test3.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public PostDTO createPost(Post post, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found :("));

        post.setUser(post.isAnonymous() ? null : user); // null user for confession
        post.setCreatedAt(LocalDateTime.now());

        Post savedPost = postRepository.save(post);

        return PostDTO.builder()
                .id(savedPost.getId())
                .content(savedPost.getContent())
                .imageUrl(savedPost.getImageUrl())
                .anonymous(savedPost.isAnonymous())
                .createdAt(savedPost.getCreatedAt().toString())
                .username(savedPost.isAnonymous() ? "Anonymous Post" : user.getName())
                .profilePicture(savedPost.isAnonymous() ? "Anonymous Post" : user.getProfilePicture())
                .build();
    }

    // 🧠 feed logic (non-paginated)
    public List<FeedDTO> getFeedPosts() {
        List<Post> posts = postRepository.findAll();
        posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));


        return posts.stream().map(post -> FeedDTO.builder()
                .id(post.getId())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .anonymous(post.isAnonymous())
                .createdAt(post.getCreatedAt().toString())
                .username(post.isAnonymous() ? null : post.getUser().getName())
                .profilePicture(post.isAnonymous() ? null : post.getUser().getProfilePicture())
                .build()).collect(Collectors.toList());
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public PostDTO updatePost(Integer postId, Integer userId, Post updatedData) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (post.getUser() != null && (post.getUser().getId().equals(userId) || user.isAdmin())) {
            post.setContent(updatedData.getContent());
            post.setImageUrl(updatedData.getImageUrl());
            postRepository.save(post);

            return PostDTO.builder()
                    .id(post.getId())
                    .content(post.getContent())
                    .imageUrl(post.getImageUrl())
                    .anonymous(post.isAnonymous())
                    .createdAt(post.getCreatedAt().toString())
                    .username(post.isAnonymous() ? null : user.getName())
                    .profilePicture(post.isAnonymous() ? null : user.getProfilePicture())
                    .build();
        } else {
            throw new RuntimeException("Unauthorized to update this post");
        }
    }

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public String deletePost(Integer postId, Integer userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (post.getUser() != null && (post.getUser().getId().equals(userId) || user.isAdmin())) {
            commentRepository.deleteAllByPost(post);
            postRepository.delete(post);
            return "Post and its comments deleted successfully.";
        } else {
            throw new RuntimeException("Unauthorized to delete this post");
        }
    }

    public void createPostWithImage(Integer userId, String content, MultipartFile file, boolean anonymous) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setContent(content);
        post.setAnonymous(anonymous);
        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());

        if (file != null && !file.isEmpty()) {
            try {
                String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path path = Paths.get("src/main/resources/static/images/" + filename);
                Files.createDirectories(path.getParent());
                Files.write(path, file.getBytes());
                post.setImageUrl("/images/" + filename);
            } catch (IOException e) {
                throw new RuntimeException("Failed to save image");
            }
        }

        postRepository.save(post);
    }
}

package com.kpugram.test3.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Post entity represents a content post created by a user.
 * A post may optionally include an image and can be marked as anonymous.
 * Each post may have multiple comments and likes.
 *
 * Fields:
 *   - id          : Unique identifier for the post
 *   - content     : Text content of the post
 *   - imageUrl    : Optional image associated with the post
 *   - anonymous   : Indicates whether the post is anonymous
 *   - createdAt   : Timestamp when the post was created
 *   - user        : The user who created the post (nullable if anonymous)
 *   - comments    : List of comments associated with the post
 *   - likes       : List of likes associated with the post
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private String imageUrl;

    private boolean anonymous;

    private LocalDateTime createdAt;

    /**
     * Many-to-one relationship with User.
     * This represents the user who created the post.
     * If the post is anonymous, this field may be null.
     */
    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * One-to-many relationship with Comment.
     * A post can have many comments.
     * Cascade operations apply and orphan comments are automatically removed.
     */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    /**
     * One-to-many relationship with Like.
     * A post can receive many likes.
     * Cascade operations apply and orphan likes are automatically removed.
     */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes;
}

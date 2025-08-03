package com.kpugram.test3.models;

import jakarta.persistence.*;
import lombok.*;

/**
 * Like entity represents a "like" action performed by a user on a post.
 * Each entry maps a user to a post, indicating that the user liked the post.
 * Stored in the "likes" table.
 *
 * Fields:
 *   - id    : Unique identifier for the like (auto-generated)
 *   - user  : The user who liked the post
 *   - post  : The post that was liked
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Many-to-one relationship to User.
     * Indicates which user performed the like.
     * Mapped via "user_id" foreign key.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Many-to-one relationship to Post.
     * Indicates which post was liked.
     * Mapped via "post_id" foreign key.
     */
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}

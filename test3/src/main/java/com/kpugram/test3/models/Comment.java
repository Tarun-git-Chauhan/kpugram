package com.kpugram.test3.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Comment entity represents a comment made by a user on a post.
 * It is stored in the "comments" table and linked to both the user and the post entities.
 *
 * Fields:
 *   - id         : Unique identifier for the comment (auto-generated)
 *   - content    : Text content of the comment
 *   - createdAt  : Timestamp when the comment was created
 *   - post       : The post to which this comment belongs
 *   - user       : The user who authored the comment
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private LocalDateTime createdAt;

    /**
     * Many-to-one relationship between Comment and Post.
     * A post can have many comments.
     * This defines the foreign key "post_id" in the comments table.
     */
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    /**
     * Many-to-one relationship between Comment and User.
     * A user can write many comments.
     * This defines the foreign key "user_id" in the comments table.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

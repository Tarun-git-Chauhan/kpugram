package com.kpugram.test3.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * User entity represents a registered user of the application.
 * Includes personal information, credentials, and relationships
 * to posts, comments, and likes made by the user.
 *
 * Fields:
 *   - id              : Unique identifier for the user
 *   - name            : User's full name
 *   - password        : User's encrypted password (used only internally)
 *   - email           : Email address (used for login and identification)
 *   - bio             : Short biography or profile description
 *   - profilePicture  : URL to the user's profile image
 *   - isAdmin         : Flag indicating whether the user has administrative privileges
 *   - loginCount      : Number of times the user has logged in (starts at 1)
 *   - posts           : List of posts authored by this user
 *   - comments        : List of comments authored by this user
 *   - likes           : List of likes made by this user
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String password;
    private String email;
    private String bio;
    private String profilePicture;
    private boolean isAdmin;

    private Integer loginCount = 1;

    /**
     * One-to-many relationship with Post.
     * Represents all posts created by this user.
     * Cascade operations and orphan removal are enabled.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    /**
     * One-to-many relationship with Comment.
     * Represents all comments made by this user.
     * Cascade operations and orphan removal are enabled.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    /**
     * One-to-many relationship with Like.
     * Represents all likes made by this user.
     * Cascade operations and orphan removal are enabled.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes;
}

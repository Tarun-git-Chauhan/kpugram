package com.kpugram.test3.dto;

import lombok.*;

import java.util.List;

/**
 * UserProfileDTO extends the concept of UserDTO by including a user's post history.
 * It is used to return full profile details, typically for viewing on a user's profile page.
 *
 * This DTO includes:
 *   - Basic user information (excluding sensitive fields like password)
 *   - A list of posts authored by the user (as PostDTOs)
 *
 * Fields:
 *   - id             : Unique identifier of the user
 *   - name           : Full name
 *   - email          : Email address
 *   - profilePicture : URL to profile image
 *   - bio            : Short biography or about section
 *   - isAdmin        : Whether the user is an administrator
 *   - loginCount     : Total number of times the user has logged in
 *   - posts          : List of PostDTOs authored by this user
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDTO {
    private Integer id;
    private String name;
    private String email;
    private String profilePicture;
    private String bio;
    private boolean isAdmin;
    private Integer loginCount;

    // All posts created by this user
    private List<PostDTO> posts;
}

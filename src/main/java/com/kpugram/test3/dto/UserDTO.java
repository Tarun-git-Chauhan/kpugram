package com.kpugram.test3.dto;

import com.kpugram.test3.models.User;
import lombok.*;

/**
 * UserDTO is a simplified and secure version of the User entity used for API responses.
 * It excludes sensitive fields like passwords and is used to safely transfer user data
 * from the backend to the frontend.
 *
 * This DTO is particularly useful in login responses, admin user listings,
 * and profile views where user-related information needs to be displayed
 * without exposing confidential data.
 *
 * Fields:
 *   - id             : Unique identifier for the user
 *   - name           : Full name of the user
 *   - email          : Email address
 *   - profilePicture : URL to the user's profile image
 *   - bio            : User's biography or description
 *   - isAdmin        : Flag indicating if the user has admin privileges
 *   - loginCount     : Number of times the user has logged in
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Integer id;
    private String name;
    private String email;
    private String profilePicture;
    private String bio;
    private boolean isAdmin;
    private Integer loginCount;

    /**
     * Constructor to convert a User entity into a UserDTO.
     * Excludes the password and any other sensitive information.
     *
     * @param user The User entity from the database
     */
    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.profilePicture = user.getProfilePicture();
        this.bio = user.getBio();
        this.isAdmin = user.isAdmin();
        this.loginCount = user.getLoginCount();
    }

    /*
     * Note:
     * The password field is intentionally excluded from this DTO.
     * DTOs are meant for outward data transfer to clients.
     * Passwords should never be exposed or shared in API responses.
     */
}

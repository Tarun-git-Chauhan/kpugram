package com.kpugram.test3.services;

/**
 * UserService handles all user-related business logic, including:
 *   - Registering new users
 *   - Authenticating users during login
 *   - Tracking login counts
 *   - Converting User entities to safe DTOs
 *   - Fetching full user profiles
 */

import com.kpugram.test3.dto.PostDTO;
import com.kpugram.test3.dto.UserDTO;
import com.kpugram.test3.dto.UserProfileDTO;
import com.kpugram.test3.models.User;
import com.kpugram.test3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Converts a User entity into a UserDTO for safe frontend exposure.
     * This ensures no sensitive fields like password are exposed.
     *
     * @param user The User entity to convert
     * @return UserDTO containing public user data
     */
    public UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .profilePicture(user.getProfilePicture())
                .bio(user.getBio())
                .isAdmin(user.isAdmin())
                .loginCount(user.getLoginCount())
                .build();
    }

    /**
     * Registers a new user in the system.
     * Hashes the password and initializes login count.
     * Preserves admin flag if explicitly set.
     *
     * @param user User object containing registration data
     * @return UserDTO after successful registration
     */
    public UserDTO registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLoginCount(0);

        if (!user.isAdmin()) {
            user.setAdmin(false); // fallback if admin not explicitly set
        }

        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    /**
     * Logs in a user by verifying their email and password.
     * If successful, increments login count and returns the authenticated user.
     *
     * @param email    User's login email
     * @param password Raw password input
     * @return Optional containing the authenticated user or empty if failed
     */
    public Optional<User> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            User loggedInUser = user.get();
            Integer currentCount = loggedInUser.getLoginCount() == null ? 0 : loggedInUser.getLoginCount();
            loggedInUser.setLoginCount(currentCount + 1);
            userRepository.save(loggedInUser); // Save updated login count
            return Optional.of(loggedInUser);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Retrieves a full user profile, including the user's posts.
     * Used on the profile page to show public and anonymous posts.
     *
     * @param userId ID of the user to fetch
     * @return UserProfileDTO containing full user details and list of PostDTOs
     */
    public UserProfileDTO getUserProfile(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        User user = userOptional.get();

        List<PostDTO> postDTOs = user.getPosts().stream()
                .map(post -> PostDTO.builder()
                        .id(post.getId())
                        .content(post.getContent())
                        .imageUrl(post.getImageUrl())
                        .anonymous(post.isAnonymous())
                        .createdAt(String.valueOf(post.getCreatedAt()))
                        .username(post.isAnonymous() ? null : user.getName())
                        .profilePicture(post.isAnonymous() ? null : user.getProfilePicture())
                        .build())
                .toList();

        return UserProfileDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .profilePicture(user.getProfilePicture())
                .bio(user.getBio())
                .isAdmin(user.isAdmin())
                .loginCount(user.getLoginCount())
                .posts(postDTOs)
                .build();
    }
}

package com.kpugram.test3.services;
/*
This service does 3 key things:
Converts User -> UserDTO (safe for frontend)
Registers new users
Logs in users
*


The service layer holds all the logic for registering and logging in users.
It also converts sensitive User data into safe UserDTOs before sending any response.
* */

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

    // lets convert User Entity to DTO
    public UserDTO convertToDTO(User user){
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


    // Lets register the new User like a sign up
    public UserDTO registerUser(User user){
        /*// We are hashing the password as well
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLoginCount(0);
        user.setAdmin(user.isAdmin()); // Explicitly carry over admin flag
        User savedUser = userRepository.save(user);

        return convertToDTO(savedUser);*/


            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setLoginCount(0);

            // ✅ preserve the admin flag if provided
            if (user.isAdmin() == false) {
                user.setAdmin(false); // fallback if not set
            }
//        if (user.getProfilePicture() == null || user.getProfilePicture().isEmpty()) {
//            user.setProfilePicture("http://localhost:8080/images/blank.png");
//        }
            User savedUser = userRepository.save(user);
            return convertToDTO(savedUser);

    }

    // login use by email and verify password
    public Optional<User> login(String email, String password){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())){
////            return Optional.of(user.get());
//            return user;

            User loggedInUser = user.get();
//            loggedInUser.setLoginCount(loggedInUser.getLoginCount() + 1); //  Increase login streak
            Integer currentCount = loggedInUser.getLoginCount() == null ? 0 : loggedInUser.getLoginCount();
            loggedInUser.setLoginCount(currentCount + 1);
            userRepository.save(loggedInUser); //  Save updated login count
            return Optional.of(loggedInUser);
        }
        else return Optional.empty();
    }
//    Whenever a user logs in successfully, increase loginCount by 1:

    // Get full user profile with posts
    public UserProfileDTO getUserProfile(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        User user = userOptional.get();

        // Fetch all posts from the user (assumes getPosts() is mapped in User class)
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

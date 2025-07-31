package com.kpugram.test3.dto;

import lombok.*;

import java.util.List;

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

/*Notes for us:
Inherits everything UserDTO had

Adds a List<PostDTO> field to hold all the user's posts (like a grid on Instagram)

Uses Lombok to reduce boilerplate (@Builder, @Getter, etc.)
*
* */

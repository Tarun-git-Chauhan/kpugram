package com.kpugram.test3.dto;
/*We don’t want to expose passwords or other sensitive info to the frontend
The DTO helps us send only what the frontend needs
 because

 The UserDTO is a simplified version of the User entity.
 It hides sensitive fields like password and is used for safely sending data to the frontend.

*/
import lombok.*;

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
    /*May be you guys wondering why we didn't user the
    * private String password;
    *
    * because
    * We exclude passwords from UserDTO to protect user data.
    * DTOs are for responding to the frontend, not collecting login info.
    * Passwords should never leave the backend after authentication.*/


}

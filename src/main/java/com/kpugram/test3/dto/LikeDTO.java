package com.kpugram.test3.dto;

import lombok.Builder;
import lombok.Data;

/**
 * LikeDTO is a Data Transfer Object used to represent users
 * who have liked a particular post.
 *
 * This DTO is returned when fetching the list of likes for a post.
 * It avoids exposing unnecessary user data by including only basic identifiers.
 *
 * Fields:
 *   - id             : Unique identifier of the like entry
 *   - username       : Username of the user who liked the post
 *   - profilePicture : Profile image URL of the user
 */
@Data
@Builder
public class LikeDTO {
    private Integer id;
    private String username;
    private String profilePicture;
}

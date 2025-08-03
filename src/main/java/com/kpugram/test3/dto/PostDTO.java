package com.kpugram.test3.dto;

import lombok.Builder;
import lombok.Data;

/**
 * PostDTO is a Data Transfer Object used to send post data to the frontend.
 * It is used in both normal and anonymous post contexts, such as the feed and confessions.
 *
 * This DTO helps isolate frontend display logic from internal Post model structures.
 *
 * Fields:
 *   - id             : Unique identifier of the post
 *   - content        : Text content of the post
 *   - imageUrl       : Optional image URL attached to the post
 *   - username       : Username of the post creator (null if anonymous or not needed)
 *   - anonymous      : Flag indicating whether the post is anonymous
 *   - profilePicture : Profile image URL of the user (null if anonymous)
 *   - createdAt      : Timestamp of post creation
 */
@Data
@Builder
public class PostDTO {
    private Integer id;
    private String content;
    private String imageUrl;
    private String username; // Included only for non-anonymous posts in the feed
    private boolean anonymous;
    private String profilePicture;
    private String createdAt;
}

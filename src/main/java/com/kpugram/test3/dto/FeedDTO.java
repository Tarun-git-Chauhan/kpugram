package com.kpugram.test3.dto;

import lombok.Builder;
import lombok.Data;

/**
 * FeedDTO is a Data Transfer Object used to structure post data for the frontend feed.
 * It includes both anonymous and non-anonymous posts.
 *
 * This format ensures that only the necessary information is sent to the frontend,
 * helping maintain privacy for anonymous posts and reducing payload size.
 *
 * Fields:
 *   - id             : Unique identifier of the post
 *   - content        : The textual content of the post
 *   - imageUrl       : Optional image URL associated with the post
 *   - anonymous      : Flag indicating if the post is anonymous
 *   - createdAt      : Timestamp when the post was created
 *   - username       : Username of the poster (null if anonymous)
 *   - profilePicture : Profile image URL of the poster (null if anonymous)
 */
@Data
@Builder
public class FeedDTO {
    private Integer id;
    private String content;
    private String imageUrl;
    private boolean anonymous;
    private String createdAt;

    // These fields are included only when the post is not anonymous
    private String username;
    private String profilePicture;
    private Long likeCount; // new update where i added the the like count for the feed.
}

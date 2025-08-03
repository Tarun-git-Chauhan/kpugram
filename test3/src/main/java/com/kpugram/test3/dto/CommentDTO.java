package com.kpugram.test3.dto;

/**
 * CommentDTO is a lightweight Data Transfer Object used to send
 * comment-related information to the frontend.
 *
 * It is used instead of returning the full Comment or User entity
 * to improve performance and avoid exposing sensitive data.
 *
 * Fields included:
 *   - id             : Unique identifier of the comment
 *   - content        : The actual comment text
 *   - createdAt      : Timestamp of when the comment was created
 *   - username       : Username of the commenter
 *   - profilePicture : Profile image URL of the commenter
 */
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDTO {
    private Integer id;
    private String content;
    private String createdAt;
    private String username;
    private String profilePicture;
}

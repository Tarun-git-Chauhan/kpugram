package com.kpugram.test3.dto;

/**
 * CommentRequest is a Data Transfer Object used to receive
 * comment creation requests from the frontend.
 *
 * This DTO is used when a user submits a new comment, typically in JSON format.
 *
 * Fields expected from the client:
 *   - postId   : ID of the post the comment is related to
 *   - userId   : ID of the user submitting the comment
 *   - content  : The actual comment text
 */
import lombok.Data;

@Data
public class CommentRequest {
    private Integer postId;
    private Integer userId;
    private String content;
}

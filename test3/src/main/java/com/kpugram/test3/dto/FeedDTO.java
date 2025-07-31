package com.kpugram.test3.dto;

import lombok.Builder;
import lombok.Data;
// this is the format backend want to send to the frontend
// it helps us to the backend logic
@Data
@Builder
public class FeedDTO {
    private Integer id;
    private String content;
    private String imageUrl;
    private boolean anonymous;
    private String createdAt;

    // show only if not Anonymous
    private String username;
    private String profilePicture;
    // if the post is anonymous then we will only return the niull
}

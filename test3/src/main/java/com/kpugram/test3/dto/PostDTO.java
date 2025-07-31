package com.kpugram.test3.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDTO {
    private Integer id;
    private String content;
    private String imageUrl;
    private String username; // for feed display not in the confession session
    private boolean anonymous;
    private String profilePicture;
    private String createdAt;
}

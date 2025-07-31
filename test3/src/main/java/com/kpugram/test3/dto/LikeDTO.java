package com.kpugram.test3.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikeDTO {
    private Integer id;
    private String username;
    private String profilePicture;
}

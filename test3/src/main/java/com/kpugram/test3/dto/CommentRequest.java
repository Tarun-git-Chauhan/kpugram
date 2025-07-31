package com.kpugram.test3.dto;
// this dto we will use to recive a requeest in the json
import lombok.Data;

@Data
public class CommentRequest {
    private Integer postId;
    private Integer userId;
    private String content;
}

package com.kpugram.test3.dto;
/*We don’t want to return full User/Post object in API — too heavy & risky.

Instead, we send only what frontend needs:

Comment text

Who commented (username + profile picture)

Time of comment

* */

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

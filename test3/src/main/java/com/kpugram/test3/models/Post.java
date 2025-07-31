package com.kpugram.test3.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content; // this is the context what it is

    private String imageUrl; // optional images if the user want to upload it
    private boolean anonymous; // is this post is personal or anonymous

    private LocalDateTime createdAt; // timestamp when the post is created

    @ManyToOne
    @JoinColumn(name = "user_id") // this is like a foreign key
    // the person who posted (null if anonymoux is true)
    private User user;
}


/*to test in the postman
* {
  "content": "My first post on KPUgram!",
  "imageUrl": "https://example.com/image.jpg",
  "anonymous": false
}
*
* */

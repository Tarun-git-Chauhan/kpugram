package com.kpugram.test3.models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
Like.java	-> Entity for storing who liked which post
LikeDTO.java	-> For safe display
LikeRepository.java -> 	To interact with DB
LikeService.java	-> Business logic
LikeController.java	-> API endpoints to like, get likes, count likes
SecurityConfig.java	-> Add permission for /api/likes/**
*/
@Entity
@Data
@NoArgsConstructor // for faster object creation
@AllArgsConstructor
@Builder // this helpo to auto-generate the setters and getters and builder pattern from Lombok
@Table(name = "likes",
        uniqueConstraints = { // this will help to stop the user from liking the same post twice
        @UniqueConstraint(columnNames = {"user_id", "post_id"})
})

public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}

/*
to like the post we can use this format of the api

http://localhost:8080/api/likes/like?userId=1&postId=1

i tried into the postman and it works :)

*
* */


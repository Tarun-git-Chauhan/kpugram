package com.kpugram.test3.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    //	This marks id as the primary key and lets the DB auto-increment it.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;
    private LocalDateTime createdAt;

    // 	A user can have many comments, but each comment belongs to one user.
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //A post can have many comments, but each comment is linked to one post.
    @ManyToOne
    @JoinColumn(name = "post_id")
    @OnDelete(action = OnDeleteAction.CASCADE) // this line is important while deleting the post it will delete the comments as well
    private Post post;

//    This creates a foreign key column in the comment table pointing to user. Same for post_id
}

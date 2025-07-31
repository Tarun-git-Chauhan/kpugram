package com.kpugram.test3.repositories;

import com.kpugram.test3.models.Comment;
import com.kpugram.test3.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;


/*JpaRepository gives us built-in DB methods: save(), findAll(), deleteById(),
 etc.

findByPostId is a custom method: Spring auto-generates the SQL based on method name.

It finds all comments where comment.post.id == postId.


* */
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
    List<Comment> findByPostId(Integer postId);
    void deleteAllByPost(Post post);
// to delete the comments from the post when anyone delete the post
}

package com.kpugram.test3.repositories;

import com.kpugram.test3.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
* postRepository.getFeedPosts() → Gets all posts from DB.

posts.stream().map(...).collect(...) → Converts each Post into a FeedDTO.

post.isAnonymous() → Checks if the post is anonymous.

If anonymous, we don’t return user info — that’s why:

java
Copy code
post.isAnonymous() ? null : post.getUser().getName()
This keeps user identity hidden while still showing the post.
* */

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUserId(Integer userId);

    // for the feed
    @Query("SELECT p from Post p ORDER BY p.id DESC") // or createAt
    List<Post> getFeedPosts();
}

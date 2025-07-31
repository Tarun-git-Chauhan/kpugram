package com.kpugram.test3.repositories;

/*
This will help us talk to the database to:

Save likes

Check if a user already liked a post

Count likes

Fetch all users who liked a post
*/
import com.kpugram.test3.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    Optional<Like> findByUserIdAndPostId(Integer userId, Integer postId);
    List<Like> findByPostId(Integer postId);
    Long countByPostId(Integer postId);
}

// This interface will be auto-implemented by Spring at runtime. So no SQL needed
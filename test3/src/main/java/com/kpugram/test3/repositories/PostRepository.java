package com.kpugram.test3.repositories;

import com.kpugram.test3.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * PostRepository provides database access methods for the Post entity.
 * It extends JpaRepository to support built-in operations like:
 *   - save()
 *   - findAll()
 *   - findById()
 *   - deleteById()
 *
 * It also includes custom queries tailored for application use cases such as
 * retrieving posts by user or fetching the home feed.
 */
public interface PostRepository extends JpaRepository<Post, Integer> {

    /**
     * Retrieves all posts created by a specific user.
     *
     * @param userId ID of the user
     * @return List of posts authored by the given user
     */
    List<Post> findByUserId(Integer userId);

    /**
     * Custom query to fetch all posts ordered by most recent first.
     * This is used for rendering the home feed in reverse chronological order.
     *
     * @return List of all posts ordered by ID in descending order
     */
    @Query("SELECT p FROM Post p ORDER BY p.id DESC")
    List<Post> getFeedPosts();
}

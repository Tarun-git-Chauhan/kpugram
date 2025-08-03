package com.kpugram.test3.repositories;

import com.kpugram.test3.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * LikeRepository provides database access methods for the Like entity.
 * It extends JpaRepository to include built-in CRUD operations such as:
 *   - save()
 *   - findAll()
 *   - deleteById()
 *   - findById()
 *
 * Custom methods are defined for like-specific operations, and
 * Spring Data JPA auto-generates their implementations based on method names.
 */
public interface LikeRepository extends JpaRepository<Like, Integer> {

    /**
     * Finds a Like entity by userId and postId.
     * Used to check if a user has already liked a specific post.
     *
     * @param userId ID of the user
     * @param postId ID of the post
     * @return Optional containing the Like if it exists
     */
    Optional<Like> findByUserIdAndPostId(Integer userId, Integer postId);

    /**
     * Retrieves all Like entities for a given post.
     *
     * @param postId ID of the post
     * @return List of Like objects associated with the post
     */
    List<Like> findByPostId(Integer postId);

    /**
     * Counts the total number of likes for a specific post.
     *
     * @param postId ID of the post
     * @return Total number of likes as a Long value
     */
    Long countByPostId(Integer postId);
}

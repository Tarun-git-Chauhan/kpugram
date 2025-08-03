package com.kpugram.test3.repositories;

import com.kpugram.test3.models.Comment;
import com.kpugram.test3.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * CommentRepository provides database access methods for the Comment entity.
 * It extends JpaRepository to inherit common CRUD operations such as:
 *   - save()
 *   - findAll()
 *   - findById()
 *   - deleteById()
 *
 * Additionally, it includes custom methods for application-specific use cases.
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    /**
     * Finds all comments associated with a specific post.
     * Spring Data JPA automatically implements this based on method name.
     *
     * @param postId ID of the post
     * @return List of Comment entities related to the given post
     */
    List<Comment> findByPostId(Integer postId);

    /**
     * Deletes all comments associated with a specific post.
     * This is useful when a post is deleted and its comments must also be removed.
     *
     * @param post The Post entity whose comments should be deleted
     */
    void deleteAllByPost(Post post);
}

package com.kpugram.test3.repositories;

/**
 * UserRepository provides database access methods for the User entity.
 * It extends JpaRepository to inherit all basic CRUD operations such as:
 *   - save()
 *   - findAll()
 *   - findById()
 *   - deleteById()
 *
 * A custom query method is also provided to support login and signup workflows.
 */

import com.kpugram.test3.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Finds a user by their email address.
     * Used for authentication (login) and registration checks.
     *
     * @param email The user's email address
     * @return An Optional containing the User if found, or empty if not
     */
    Optional<User> findByEmail(String email);
}

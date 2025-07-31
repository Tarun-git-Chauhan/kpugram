package com.kpugram.test3.repositories;
/*this interface gives us the option like CRUD save(), deleter()
*
The extra findByEmail() lets
* us log in or check if a user already exists by email
* (used during signup/login)
* */

import com.kpugram.test3.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email); // for login and verification
}

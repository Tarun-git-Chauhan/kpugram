package com.kpugram.test3.models;

import jakarta.persistence.*;
/* @Entity comes from this Jakarata Persistence, which used for the mapping
* Java classes to DB tables*/
import lombok.*;

import java.util.ArrayList;
import java.util.List;
/* Lombok annotations like @Getter, @Setter reduce boilerplate ,, jsut for us :)
* ----
* We're using Jakarta's JPA to map Java classes to relational database tables.
* Lombok simplifies our code by auto-generating getters, setters, and constructors.
* */

@Entity // defines that this class will be a table in the DB
@Table(name = "users") // specifies exact table name in the DB
@Getter
@Setter // auto-gen get/ set methods  ... just to make our work easy like we did test 1 and 2
@NoArgsConstructor // for object creation
@AllArgsConstructor //for object creation
@Builder // allows .builder() style object creation
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // we are keeping the name as a single string for now.
    @Column(nullable = false)
    private String name; // full name (e.g., "Tarun Chauhan"

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "profile_picture", columnDefinition = "TEXT")
    private String profilePicture;

    @Column(length = 500)
    private String bio;

    private boolean isAdmin;

    // for the profile page
    @Column(name = "login_count")
    private Integer loginCount=0;

    // posts get inside the profile bio
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();
}

/*
* For each user, we want to:

Display basic info: name, email, bio, profile picture, isAdmin

Show all posts made by that user (like Instagram grid)

Track number of times the user logged in (Login streak count)
* */

// SecurityConfig.java
// Configures basic security settings for the application using Spring Security.
// Includes password encryption and security rules for handling HTTP requests.

package com.kpugram.test3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Indicates this class provides Spring configuration
public class SecurityConfig {

    // Bean to define the password encoder used for hashing user passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configures the security filter chain for HTTP requests
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Disables CSRF (Cross-Site Request Forgery) protection for development/testing
        http.csrf(AbstractHttpConfigurer::disable)

                // Defines authorization rules for incoming requests
                .authorizeHttpRequests(auth -> auth
                        /*
                        // Allows unauthenticated access to the following endpoint patterns
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/posts/**").permitAll()
                        .requestMatchers("/api/comments/**").permitAll()
                        .requestMatchers("/api/likes/**").permitAll()
                        .requestMatchers("/api/profile/**").permitAll()
                        */

                        // Temporary configuration: allows all requests without authentication
                        .anyRequest().permitAll()
                );

        // Builds and returns the configured SecurityFilterChain
        return http.build();
    }
}

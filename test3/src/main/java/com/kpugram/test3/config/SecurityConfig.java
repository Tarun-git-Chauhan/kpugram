package com.kpugram.test3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        CSRF: Cross-Site Request Forgery
        http.csrf(AbstractHttpConfigurer::disable) // disable CSRF for testing
                .authorizeHttpRequests(auth -> auth
                       /* .requestMatchers("/api/auth/**").permitAll() // allow register & login
                        .requestMatchers("/api/posts/**").permitAll() // allow posts creation
                        .requestMatchers("/api/comments/**").permitAll()
                        .requestMatchers("/api/likes/**").permitAll()
                        .requestMatchers("/api/profile/**").permitAll()
*/
                        // commented for testing time temporary


//                        .anyRequest().authenticated()// protect everything else
                                .anyRequest().permitAll()
                );
        return http.build();
    }


}

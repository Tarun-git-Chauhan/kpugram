// WebConfig.java
// Configuration class to resolve CORS issues, particularly when fetch requests
// work in Postman but fail in the browser due to cross-origin restrictions.

package com.kpugram.test3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Marks this class as a configuration class for Spring Boot
public class WebConfig {

    @Bean // Registers this method's return object as a bean in the Spring context
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            // Adds CORS mappings to allow frontend requests from a different origin
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Applies CORS configuration to all API endpoints
                        .allowedOrigins("https://kpugram.netlify.app") // Allows frontend served from this origin (e.g., Live Server)
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Specifies allowed HTTP methods
                        .allowedHeaders("*") // Allows all request headers
                        .allowCredentials(true); // Allows sending of credentials such as cookies
            }
             // ✅ This part will allow serving files from /Images/** URL
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/Images/**")
                        .addResourceLocations("file:/opt/render/project/src/Images/");
            }

        };
    }
}

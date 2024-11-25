package com.pragya.oimspro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://13.60.229.204/") // Allow your production Angular origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Adjust allowed HTTP methods as needed
                .allowedHeaders("*") // Adjust allowed headers as needed
                .allowCredentials(true); // Consider carefully whether to allow credentials in production
    }
}

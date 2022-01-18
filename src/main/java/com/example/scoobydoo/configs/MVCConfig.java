package com.example.scoobydoo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file://" + uploadPath() + System.getProperty("file.separator"));
    }

    @Bean
    public String uploadPath() {
        return System.getProperty("user.dir") + System.getProperty("file.separator") + "uploads";
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

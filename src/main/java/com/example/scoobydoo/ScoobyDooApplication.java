package com.example.scoobydoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan("com.example.scoobydoo.entities")
@EnableJpaRepositories(basePackages = "com.example.scoobydoo.repos")
@SpringBootApplication
public class ScoobyDooApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ScoobyDooApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ScoobyDooApplication.class);
    }
}

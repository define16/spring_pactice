package com.example.jqa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JqaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JqaApplication.class, args);
    }

}

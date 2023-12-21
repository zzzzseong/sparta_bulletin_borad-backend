package com.sparta.spartabulletinboardbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TODOBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TODOBackendApplication.class, args);
    }

}

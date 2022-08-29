package com.example.theproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TheProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheProjectApplication.class, args);
    }

}

package com.fastcampus.boardproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan //thymeleaf3 configurationPropertiesScan 필요
@SpringBootApplication
public class FcBoardProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FcBoardProjectApplication.class, args);
    }

}

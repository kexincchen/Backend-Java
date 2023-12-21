package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        Press press = new Press();
        Ad ad = new Ad();
        Comments comments = new Comments();
        SpringApplication.run(Application.class, args);
    }

}


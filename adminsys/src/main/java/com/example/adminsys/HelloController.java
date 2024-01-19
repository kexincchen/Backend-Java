package com.example.adminsys;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return("<h1>Welcome</h1>");
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }


}
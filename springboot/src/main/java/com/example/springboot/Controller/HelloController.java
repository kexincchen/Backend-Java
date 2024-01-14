package com.example.springboot.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return("<h1>Welcome</h1>");
    }

    @GetMapping("/hello")
    public String hello(Principal principal) {
        return "Hello," + principal.getName();
    }

    @GetMapping("/admin/hello")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user/hello")
    @PreAuthorize("hasRole('USER')")
    public String user() {
        return "user";
    }

}

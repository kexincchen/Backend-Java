package com.example.springboot.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(false); // do not create one
        if (session == null) {
            System.out.println("Session is null.");
        } else {
            System.out.println("Session id = " + session.getId());
        }
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

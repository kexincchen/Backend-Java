package com.example.springboot.Controller;

import com.example.springboot.Entity.User;
import com.example.springboot.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {

    @GetMapping("/userinfo")
    public Result getUserInfo() {
        User userInfoRes = new User();
        userInfoRes.setUsername("Clareee");
        return Result.ok(userInfoRes);
    }
}


package com.example.springboot.Controller;


import com.example.springboot.ServiceImpl.JwtService;
import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/* Test the token generation */
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    JwtService jwtService;

    // Login REST API
    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestParam String username, @RequestParam String password){
        JSONObject json=new JSONObject();
        // TODO: Validate usr
//        String token = jwtTokenUtil.generateJwtToken(new User(username, password, "ADMIN"));
        String token = jwtService.generateToken(username, "USER");
        json.put("success", true);
        json.put("code", 1);
        //json.put("result", user1);
        json.put("time", new Date());
        json.put("message", "Login Successfully!");
        json.put("Authorization", token);

        return ResponseEntity.ok(json.toJSONString());
    }
}

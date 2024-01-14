package com.example.springboot.Controller;


import com.example.springboot.Entity.User;
import com.example.springboot.Util.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    JwtTokenUtil jwtTokenUtil;

    // Login REST API
    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestParam String username, @RequestParam String password){

        // TODO: Validate usr
        String token = jwtTokenUtil.generateJwtToken(new User(username, password, "ADMIN"));
        return ResponseEntity.ok(token);
    }
}

package com.example.springboot.Controller;

import com.example.springboot.Entity.User;
import com.example.springboot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestParam String nickname, @RequestParam String phoneNumber) {
        try {
            User user = userService.registerNewUser(nickname, phoneNumber);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully. \nID: " + user.getUserid());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/update-nickname")
    public ResponseEntity<String> updateUserNickname(@PathVariable Long id, @RequestParam String nickname) {
        try {
            userService.updateUserNickname(id, nickname);
            return ResponseEntity.ok("User nickname updated successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

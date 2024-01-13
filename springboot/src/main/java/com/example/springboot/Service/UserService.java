package com.example.springboot.Service;

import com.example.springboot.Entity.User;

import java.util.List;

public interface UserService {

    User registerNewUser(String nickname, String phoneNumber, String password);

    void updateUserNickname(Long id, String nickname);

    List<User> getAllUsers();

    User findByUsername(String username);
}

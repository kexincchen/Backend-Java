package com.example.springboot.Service;

import com.example.springboot.Entity.Press;
import com.example.springboot.Entity.User;
import com.example.springboot.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User registerNewUser(String nickname, String phoneNumber) {
        User user = new User(nickname, phoneNumber);
        userMapper.insert(user);
        return user;
    }

    public void updateUserNickname(Long id, String nickname) {
        // Fetch the existing user record
        User user = userMapper.selectById(id);

        if (user != null) {
            // Update the title and body
            user.setNickname(nickname);
            // Persist the changes
            userMapper.updateById(user);
        } else {
            throw new IllegalStateException("User not found with id: " + id);
        }
    }

    public List<User> getAllUsers(){
        return userMapper.selectList(null);
    }
}

package com.example.springboot.ServiceImpl;

import com.example.springboot.Entity.User;
import com.example.springboot.Mapper.UserMapper;
import com.example.springboot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
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

        if (user == null) {
            throw new IllegalStateException("User not found with id: " + id);
        }
        // Update the nickname
        user.setNickname(nickname);
        // Persist the changes
        userMapper.updateById(user);
    }

    public List<User> getAllUsers() {
        return userMapper.selectList(null);
    }
}

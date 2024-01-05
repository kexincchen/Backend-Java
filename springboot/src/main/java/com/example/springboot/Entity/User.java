package com.example.springboot.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Users")
public class User {
    @TableId
    private Long userID;
    private String nickname;
    private String phoneNumber;
    private String lastLogin;

    public User(String nickname, String phoneNumber){
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
}

package com.example.springboot.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Users")
public class User {
    @TableId(type = IdType.AUTO)
    private Long userid;
    private String nickname;
    @TableField("PhoneNumber")
    private String phoneNumber;
    @TableField("LastLogin")
    private String lastLogin;
    // No-argument constructor
    public User() {
    }

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

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}

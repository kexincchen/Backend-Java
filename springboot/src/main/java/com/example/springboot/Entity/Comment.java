package com.example.springboot.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Comments")
public class Comment {
    @TableId
    private Long commentID;
    private String content;
    private Long newsID;
    private Long userID;

    public Comment(String content, Long newsID, Long userID) {
        this.content = content;
        this.newsID = newsID;
        this.userID = userID;
    }
}

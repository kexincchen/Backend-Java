package com.example.springboot.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Comments")
public class Comment {
    @TableId(type = IdType.AUTO)
    private Long commentid;
    private String content;
    @TableField("NewsID")
    private Long newsID;
    @TableField("UserID")
    private Long userID;
    private Integer numlikes;
    private Integer numdislikes;

    public Comment() {
    }

    public Comment(String content, Long newsID, Long userID) {
        this.content = content;
        this.newsID = newsID;
        this.userID = userID;
        this.numdislikes = 0;
        this.numlikes = 0;
    }

    public void setCommentid(Long commentid) {
        this.commentid = commentid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNewsID(Long newsID) {
        this.newsID = newsID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setNumlikes(Integer numlikes) {
        this.numlikes = numlikes;
    }

    public void setNumdislikes(Integer numdislikes) {
        this.numdislikes = numdislikes;
    }

    @Override
    public String toString() {
        return "{" +
                "commentid=" + commentid +
                ", content='" + content + '\'' +
                ", newsID=" + newsID +
                ", userID=" + userID +
                '}';
    }
}

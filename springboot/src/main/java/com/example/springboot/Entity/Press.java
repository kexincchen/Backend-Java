package com.example.springboot.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@TableName("News")
@NoArgsConstructor
public class Press {
    @TableId(type = IdType.AUTO)
    private Long newsid;
    private String title;
    private String body;
    @TableField(exist = false)
    private List<Advertisement> advertisements;

    public Press(Long id, String title, String body) {
        this.newsid = id;
        this.title = title;
        this.body = body;
    }

    public Press(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public void setNewsid(Long newsid) {
        this.newsid = newsid;
    }

    public Long getId() {
        return newsid;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        String adDetails = advertisements == null ? "No advertisements" :
                advertisements.stream()
                        .map(Advertisement::toString)
                        .collect(Collectors.joining(", "));
        return "{" +
                "newsid=" + newsid +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", advertisements=[" + adDetails + "]" +
                '}';
    }
}


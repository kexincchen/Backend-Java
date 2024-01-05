package com.example.springboot.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("News")
public class Press {
    @TableId
    private Long newsid;
    private String title;
    private String body;

    public Press(Long id, String title, String body) {
        this.newsid = id;
        this.title = title;
        this.body = body;
    }

    public Press(String title, String body) {
        this.title = title;
        this.body = body;
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
        return "{'id': " + newsid + ", 'title': " + title + ", 'body': " + body + "}";
    }
}


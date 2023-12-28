package com.example.springboot;

public class Press {
    private int id;
    private String title;
    private String content;

    // 构造函数、getter和setter
    public Press(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }


}


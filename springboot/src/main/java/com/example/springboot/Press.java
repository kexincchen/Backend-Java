package com.example.springboot;

public class Press {
    private final int id;
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

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "{{'id': " + id + "}, {'title': " + title + "}, {'body': " + content + "}}";
    }
}


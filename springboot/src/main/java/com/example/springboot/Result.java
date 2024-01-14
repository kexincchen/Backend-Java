package com.example.springboot;

import lombok.Data;

@Data
public class Result {

    private int code = 0;

    private Object data;

    private String msg;

    public static Result ok(Object data) {
        Result result = new Result();
        result.data = data;
        return result;
    }
}


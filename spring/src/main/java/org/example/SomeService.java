package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SomeService {

    private Press press;
    private Ad ad;
    private Comments comments;

    @Autowired
    public SomeService(Press press, Ad ad, Comments comments) {
        this.press = press;
        this.ad = ad;
        this.comments = comments;
    }

    // ... 使用press, ad, 和 comments的方法
}

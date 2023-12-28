package com.example.springboot;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PressService {
    private List<Press> pressList = Arrays.asList(
            new Press(1, "示例文章", "这是一篇示例文章的内容。")
    );

    public Optional<Press> getPressById(int id) {
        return pressList.stream().filter(p -> p.getId() == id).findFirst();
    }


}

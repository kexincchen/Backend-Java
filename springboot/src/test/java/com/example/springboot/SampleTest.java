package com.example.springboot;

import com.example.springboot.Entity.Press;
import com.example.springboot.Mapper.PressMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.testng.annotations.Test;

import java.util.List;

@SpringBootTest
public class SampleTest {

    @Autowired
    private PressMapper pressMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Press> userList = pressMapper.selectList(null);
        Assertions.assertEquals(1, userList.size());
        userList.forEach(System.out::println);
    }

}

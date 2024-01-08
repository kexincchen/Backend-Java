package com.example.springboot.Service;

import com.example.springboot.Mapper.PressMapper;
import com.example.springboot.Entity.Press;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PressService {

    List<Press> getAllPress();

    Press getPressById(Long id);

    Press insertNewPress(String title, String body);

    void updatePress(Long id, String newTitle, String newBody);

    Press getPressWithAdvertisements(Long id);

    List<Press> getAllPressWithAdvertisements();

}

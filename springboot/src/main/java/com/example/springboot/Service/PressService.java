package com.example.springboot.Service;

import com.example.springboot.Mapper.PressMapper;
import com.example.springboot.Entity.Press;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PressService {

    @Autowired
    private PressMapper pressMapper;

    public List<Press> getAllPress(){
        return pressMapper.selectList(null);
    }

    public Press getPressById(Long id){
        return pressMapper.selectById(id);
    }

    public void insertNewPress(String title, String body){
        pressMapper.insert(new Press(title, body));
    }

    public void updatePress(Long id, String newTitle, String newBody) {
        // Fetch the existing press record
        Press press = pressMapper.selectById(id);

        if (press != null) {
            // Update the title and body
            press.setTitle(newTitle);
            press.setBody(newBody);

            // Persist the changes
            pressMapper.updateById(press);
        } else {
            throw new IllegalStateException("Press not found with id: " + id);
        }
    }

}

package com.example.springboot.Controller;

import com.example.springboot.Entity.Press;
import com.example.springboot.Service.PressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/press")
public class PressController {

    @Autowired
    private PressService pressService;

    @GetMapping("/{id}")
    public String getPressById(@PathVariable Long id) throws SQLException {
        Press press = pressService.getPressById(id);
        if (press != null){
            return press.toString();
        }
        return "";
    }

    @GetMapping("/all")
    public String getAllPress() {
        List<Press> presses = pressService.getAllPress();
        System.out.println(presses);
        if (presses != null){
            return presses.toString();
        }
        return "";
    }

    @GetMapping("/test")
    public String printSuccess() {
        return "Success!";
    }


}


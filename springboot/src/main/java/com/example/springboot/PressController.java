package com.example.springboot;

import com.example.springboot.Press;
import com.example.springboot.PressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Optional;

@RestController
@RequestMapping("/press")
public class PressController {

    @Autowired
    private PressService pressService;

    @GetMapping("/{id}")
    public String getPressById(@PathVariable int id) throws SQLException {
        Press press = pressService.getPressById(id);
        return press.toString();
    }

    @GetMapping("/test")
    public String printSuccess() {
        return "Success!";
    }


}


package com.example.springboot;

import com.example.springboot.Press;
import com.example.springboot.PressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/press")
public class PressController {

    @Autowired
    private PressService pressService;

    @GetMapping("/{id}")
    public ResponseEntity<Press> getPressById(@PathVariable int id) {
        Optional<Press> press = pressService.getPressById(id);
        return press.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/test")
    public String printSuccess() {
        return "Success!";
    }


}


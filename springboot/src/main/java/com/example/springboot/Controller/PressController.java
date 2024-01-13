package com.example.springboot.Controller;

import com.example.springboot.Entity.Press;
import com.example.springboot.Service.PressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/press")
public class PressController {

    @Autowired
    private PressService pressService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getPressById(@PathVariable Long id) {
//        Press press = pressService.getPressById(id);
        Press press = pressService.getPressWithAdvertisements(id);
        if (press != null){
            return ResponseEntity.ok(press.toString());
        }
        throw new IllegalStateException("Press not found");
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAllPress() {
//        List<Press> presses = pressService.getAllPress();
        List<Press> presses = pressService.getAllPressWithAdvertisements();
        System.out.println(presses);
        if (presses != null){
            return ResponseEntity.ok(presses.toString());
        }
        throw new IllegalStateException("No press records found");
    }

    @PreAuthorize("hasAnyRole('admin')")
    @PostMapping("/add")
    public ResponseEntity<String> addNewPress(@RequestParam String title, @RequestParam String body){
        Press press = pressService.insertNewPress(title, body);
        return ResponseEntity.status(HttpStatus.CREATED).body("Press posted successfully. \nID: " + press.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePress(@PathVariable Long id, @RequestParam String title, @RequestParam String body){
        pressService.updatePress(id, title, body);
        return ResponseEntity.ok("Press updated successfully");
    }
}


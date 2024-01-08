package com.example.springboot.Controller;

import com.example.springboot.Entity.Press;
import com.example.springboot.Service.PressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Press not found");
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAllPress() {
//        List<Press> presses = pressService.getAllPress();
        List<Press> presses = pressService.getAllPressWithAdvertisements();
        System.out.println(presses);
        if (presses != null){
            return ResponseEntity.ok(presses.toString());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Press not found");
    }

    @GetMapping("/test")
    public String printSuccess() {
        return "Success!";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewPress(@RequestParam String title, @RequestParam String body){
        try {
            Press press = pressService.insertNewPress(title, body);
            return ResponseEntity.status(HttpStatus.CREATED).body("Press posted successfully. \nID: " + press.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error posting press: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePress(@PathVariable Long id, @RequestParam String title, @RequestParam String body){
        try {
            pressService.updatePress(id, title, body);
            return ResponseEntity.ok("Press updated successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating press: " + e.getMessage());
        }
    }


}


package com.example.springboot.Controller;

import com.example.springboot.Entity.Advertisement;
import com.example.springboot.Service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ad")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @PostMapping("/add")
    public ResponseEntity<String> insertNewAd(@RequestParam String title,
                                              @RequestParam String textContent,
                                              @RequestParam String placement,
                                              @RequestParam Long newsId) {
        try {
            Advertisement ad = advertisementService.insertNewAd(title, textContent, placement, newsId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Advertisement created successfully. \nID: " + ad.getAdid());
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating advertisement: " + e.getMessage());
        }
    }

    @GetMapping("/by-placement")
    public ResponseEntity<List<Advertisement>> getAdByType(@RequestParam String placement) {
        try {
            List<Advertisement> ads = advertisementService.getAdByType(placement);
            return ResponseEntity.ok(ads);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}/click")
    public ResponseEntity<String> clickAdByID(@PathVariable Long id) {
        try {
            advertisementService.clickAdByID(id);
            return ResponseEntity.ok("Advertisement clicked successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error clicking advertisement: " + e.getMessage());
        }
    }
}

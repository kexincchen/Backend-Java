package com.example.springboot.Service;

import com.example.springboot.Entity.Advertisement;
import com.example.springboot.Entity.Placement;
import com.example.springboot.Mapper.AdvertisementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementService {
    @Autowired
    private AdvertisementMapper advertisementMapper;

    public void insertNewAd(String title, String textContent, String placement){
        advertisementMapper.insert(new Advertisement(title, textContent, Placement.valueOf(placement)));
    }

    public List<Advertisement> getAdByType(String placement){
        return advertisementMapper.selectByPlacement(placement);
    }

    public void clickAdByID(Long id) {
        Advertisement ad = advertisementMapper.selectById(id);
        if (ad != null){
            ad.clickOnce();
            advertisementMapper.updateById(ad);
        } else {
            throw new IllegalStateException("Advertisement not found with id: " + id);
        }

    }

}

package com.example.springboot.ServiceImpl;

import com.example.springboot.Entity.Advertisement;
import com.example.springboot.Mapper.AdvertisementMapper;
import com.example.springboot.Service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    private AdvertisementMapper advertisementMapper;

    public Advertisement insertNewAd(String title, String textContent, String placement, Long newsId){
        Advertisement ad = new Advertisement(title, textContent, placement, newsId);
        advertisementMapper.insert(ad);
        return ad;
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

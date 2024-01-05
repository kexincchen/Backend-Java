package com.example.springboot.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Advertisements")
public class Advertisement {
    @TableId
    private Long adID;
    private String title;
    private String textContent;
    private Placement placement;
    private Integer clickCount;

    public Advertisement(String title, String textContent, Placement placement) {
        this.title = title;
        this.textContent = textContent;
        this.placement = placement;
        this.clickCount = 0;
    }

    public void clickOnce(){
        clickCount += 1;
    }
}

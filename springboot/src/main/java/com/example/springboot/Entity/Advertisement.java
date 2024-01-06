package com.example.springboot.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Advertisements")
public class Advertisement {
    @TableId(type = IdType.AUTO)
    private Long adid;
    private String title;
    @TableField("TextContent")
    private String textContent;
    private Placement placement;
    @TableField("ClickCount")
    private Integer clickCount;

    public Advertisement() {
    }

    public Advertisement(String title, String textContent, String placement) {
        this.title = title;
        this.textContent = textContent;
        this.placement = Placement.fromString(placement);
        this.clickCount = 0;
    }

    public void clickOnce(){
        clickCount += 1;
    }

    public void setAdid(Long adid) {
        this.adid = adid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public void setPlacement(String placementString) {
        this.placement = Placement.fromString(placementString);
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }
}

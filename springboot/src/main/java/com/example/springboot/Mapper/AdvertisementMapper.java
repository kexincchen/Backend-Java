package com.example.springboot.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.Entity.Advertisement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdvertisementMapper extends BaseMapper<Advertisement> {

    @Select("SELECT * FROM Advertisements WHERE placement = #{placement}")
    List<Advertisement> selectByPlacement(String placement);

}

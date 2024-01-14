package com.example.springboot.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.Entity.Token;
import com.example.springboot.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TokenMapper extends BaseMapper<Token> {
    @Select("SELECT * FROM Tokens WHERE token = #{token}")
    Token findByToken(@Param("token") String token);
}

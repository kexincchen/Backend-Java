package com.example.springboot.Service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.Entity.Token;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TokenRepository extends BaseMapper<Token> {
  List<Token> findAllValidTokenByUser(Integer id);

  Optional<Token> findByToken(String token);
}

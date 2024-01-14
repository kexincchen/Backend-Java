package com.example.springboot.Service;

import com.example.springboot.Entity.Token;

import java.util.List;
import java.util.Optional;

public interface TokenRepository {
  List<Token> findAllValidTokenByUser(Long id);

  Token findByToken(String token);
}

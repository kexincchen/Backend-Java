package com.example.springboot.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    String generateToken(String userId, String role);

    /**
     * 3、解析token字符串中的权限信息
     * @param token
     * @return
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    /**
     * 4、从token中解析出username
     * @param token
     * @return
     */
    String extractUsername(String token);

    /**
     * 5、判断token是否过期
     * @param
     * @return
     */
    boolean isTokenValid(String token, UserDetails userDetails);
    /**
     * 6.1、从授权信息中获取token过期时间
     */
    Date extractExpiration(String token);
}

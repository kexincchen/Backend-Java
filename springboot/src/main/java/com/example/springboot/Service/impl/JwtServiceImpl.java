package com.example.springboot.Service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * TODO 完成JWT的验证服务
 * JWT工具类
 *
 * @author ss_419
 * @version 1.0
 * @date 2023/3/3 11:16
 */
@Service
public class JwtServiceImpl {

    public static final long TOKEN_VALIDITY = 10 * 60 * 60;
    /**
     * 创建一个最终字符串，这个字符串称为密钥
     * https://allkeysgenerator.com/
     *
     * JWT最低要求的安全级别是256bit
     */
    private static final String SECRET_KEY = "3F4428472B4B6250655368566D5971337336763979244226452948404D635166";

    public String generateToken(String userId, String role) {
        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);

        // 创建JWT Claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userId);
        claims.put("role", role);

        // 生成Token
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(currentTimeMillis + TOKEN_VALIDITY))
                .signWith(getSignInKey())
                .compact();
    }
    /**
     * 1、解析token字符串中的加密信息【加密算法&加密密钥】, 提取所有声明的方法
     * @param token
     * @return
     */
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                // 获取alg开头的信息
                .setSigningKey(getSignInKey())
                .build()
                // 解析token字符串
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 2、获取签名密钥的方法
     * @return 基于指定的密钥字节数组创建用于HMAC-SHA算法的新SecretKey实例
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 3、解析token字符串中的权限信息
     * @param token
     * @return
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 4、从token中解析出username
     * @param token
     * @return
     */
    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
        final Claims claims = Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    /**
     * 5、判断token是否过期
     * @param
     * @return
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        // 从token中获取用户名
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) &&!isTokenExpired(token);
    }

    /**
     * 6、验证token是否过期
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    /**
     * 6.1、从授权信息中获取token过期时间
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}


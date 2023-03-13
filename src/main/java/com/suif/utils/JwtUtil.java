package com.suif.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "suif.jwt")
public class JwtUtil {

    private String secret;

    private long expire;

    // 生成token
    public String createToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setExpiration(new Date(System.currentTimeMillis()+expire))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // 解析token
    public Claims parseJWT(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    // token是否过期
    public boolean isExpired(Date expiration) {
        return expiration.before(new Date());
    }
}

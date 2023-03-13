package com.suif;


import com.suif.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class TwtFindApplicationTests {

    @Resource
    JwtUtil jwtUtil;

    @Test
    void testJWTUtil(){
        String token = jwtUtil.createToken(1L);
        Claims claims = jwtUtil.parseJWT(token);
        System.out.println(claims);
    }
}

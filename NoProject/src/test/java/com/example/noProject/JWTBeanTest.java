package com.example.noProject;

import com.example.noProject.utils.JwtUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JWTBeanTest {
    @Resource
    JwtUtil jwtUtil;

    @Test
    void createAndParse() {
        String token = jwtUtil.createToken();
        System.out.println(jwtUtil.isSigned(token));
    }
}

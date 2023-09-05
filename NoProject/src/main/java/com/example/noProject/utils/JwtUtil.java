package com.example.noProject.utils;

import com.example.noProject.constants.BussinessConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.parser.Token;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JwtUtil {

    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String createToken() {
        return createToken(null);
    }

    public String createToken(Map<String, Object> claims) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(secretKey)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + BussinessConstants.MAX_SESSION_IN_SECONDS * 1000))
                .setId(UuidUtil.getUUID());
        if (claims != null) {
            jwtBuilder.setClaims(claims);
        }
        return jwtBuilder.compact();
    }

    public Jws<Claims> parseToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return claimsJws;
        } catch (JwtException e) {
            log.debug("不收信任的token：{} 原因：{}",token,e.getMessage());
            return null;
        }
    }

    public boolean isSigned(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .isSigned(token);
            return true;
        } catch (JwtException e) {
            log.debug("不收信任的token：{} 原因：{}",token,e.getMessage());
            return false;
        }
    }


}

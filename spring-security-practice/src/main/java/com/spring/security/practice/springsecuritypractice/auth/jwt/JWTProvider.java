package com.spring.security.practice.springsecuritypractice.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;


@Component
public class JWTProvider {


    private static final long ACCESS_TOKEN_EXPIRED_TIME = 1000L * 60 * 60; // 1시간
    private static final long REFRESH_TOKEN_EXPIRED_TIME = 1000L * 60L * 60L * 24L * 7L; // 7일


    @Value("${jwt.secretKey}")
    private String jwtSecretKey;


    private Key getSecretKey() {
        byte[] keyBytes = jwtSecretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String loginId, List<String> roles, long expiredTime){

        Claims claims = Jwts.claims().setSubject(loginId);
        claims.put("roles", roles);
        Date date = new Date();

        Key secretKey = getSecretKey();

        String jwt = Jwts.builder()
                .setSubject(loginId)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + expiredTime))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        return jwt;
    }

    public String createAccessToken(String loginId, List<String> roles){
        return createToken(loginId, roles, ACCESS_TOKEN_EXPIRED_TIME);
    }

    public String createRefreshToken(String loginId, List<String> roles){
        return createToken(loginId, roles, REFRESH_TOKEN_EXPIRED_TIME);

    }

    public Date extractExpiredTime(String token){

        return Jwts
                .parser()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();


    }


}

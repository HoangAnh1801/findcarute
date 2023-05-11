package com.hoanganh.carservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class JwtUtils {

    private String jwtSecret = "xtoandev";

    private int jwtExpirationMs = 3600;

    public String generateJwtToken(String userName, Set<String> permissions) {

        return Jwts.builder()
                .setSubject(userName)
                .claim("groups", permissions)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }



}

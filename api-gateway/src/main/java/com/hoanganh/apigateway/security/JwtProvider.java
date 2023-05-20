package com.hoanganh.apigateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.hoanganh.apigateway.security.Constants.SIGNING_KEY;

@Component
public class JwtProvider implements Serializable {

    public Claims getClaims(final String token) {
        try {
            Claims body = Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();
            return body;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " => " + e);
        }
        return null;
    }

    public List<String> getListRoles(JSONObject payload) {
        JSONArray groups = (JSONArray) payload.get("groups");

        return groups.stream().map(role -> (String) role).collect(Collectors.toList());
    }

    public boolean validateToken(final String token) {
        try {
            Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public String getUserNameFromJwtToken(JSONObject payload) {
        return payload.getAsString("sub");
    }

    public boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    public boolean isTokenExpired(JSONObject payload) {
        long exp = payload.getAsNumber("exp").longValue();

        return new Date(exp * 1000).before(new Date());
    }
}
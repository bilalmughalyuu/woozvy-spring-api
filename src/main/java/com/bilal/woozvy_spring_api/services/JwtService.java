
package com.bilal.woozvy_spring_api.services;

import com.bilal.woozvy_spring_api.config.JwtConfig;
import com.bilal.woozvy_spring_api.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@AllArgsConstructor
@Service
public class JwtService {

    private final JwtConfig jwtConfig;

    public String generateAccessToken(User user) {
        long expiration = Long.parseLong(
                jwtConfig.getAccessTokenExpiration()
        );

        return generateToken(user, expiration);
    }

    private String generateToken(User user, long tokenExpiration) {
        long expirationMillis = tokenExpiration * 1000;

        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(jwtConfig.getSecretKey())
                .compact();
    }

    public Claims parseToken(String token) {
        try {
            return getClaims(token);
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(jwtConfig.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenExpired(String token) {
        var claims = getClaims(token);
        Date expiration = claims.getExpiration();
        return expiration == null || expiration.before(new Date());
    }
}

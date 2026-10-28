package com.bilal.woozvy_spring_api.config;

import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@ConfigurationProperties(prefix = "spring.jwt")
@Configuration
@Data
public class JwtConfig {

    private String secret;
    private String accessTokenExpiration;
    private String refreshTokenExpiration;

    public SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
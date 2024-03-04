package com.du6ak.backend.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.time.Duration;
import java.util.Date;

@Component
public class JwtCore {
    @Value("${backend.app.secret}")
    private String secret;
    @Value("${backend.app.lifetime}")
    private Duration lifetime;

    public String generateToken(UserDetails userDetails) {
        Date issuedDate = new Date();
        Date expirationDate = new Date(issuedDate.getTime() + lifetime.toMillis());
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getNameFromJwt(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
}
package com.modelsecurity.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration-ms}")
    private long jwtExpirationMs;

    private Key getSigningKey() {
        // Detectar si parece Base64 (solo caracteres válidos y sin espacios)
        String trimmed = jwtSecret.trim();
        boolean looksBase64 = trimmed.matches("[A-Za-z0-9+/=]+") && trimmed.length() % 4 == 0;
        byte[] keyBytes;
        if (looksBase64) {
            try {
                keyBytes = Decoders.BASE64.decode(trimmed);
            } catch (IllegalArgumentException ex) {
                // Fallback a bytes directos si la decodificación falla
                keyBytes = trimmed.getBytes(java.nio.charset.StandardCharsets.UTF_8);
            }
        } else {
            keyBytes = trimmed.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        }
        // Asegurar longitud mínima (32 bytes para HS256). Si es corto, expandir repitiendo.
        if (keyBytes.length < 32) {
            byte[] expanded = new byte[32];
            for (int i = 0; i < expanded.length; i++) {
                expanded[i] = keyBytes[i % keyBytes.length];
            }
            keyBytes = expanded;
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String subject) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSubject(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}

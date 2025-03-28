package com.cromxt.zenspaceserver.service.impl;

import com.cromxt.zenspaceserver.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {

    private final String secret;
    private final Long accessTokenExpiration;
    private final Long refreshTokenExpiration;

    public JWTServiceImpl(Environment environment) {
        String privateKey = environment.getProperty("ZENSPACE_CONFIG_JWT_SECRET", String.class);
        Long accessTokenExpiration = environment.getProperty("ZENSPACE_CONFIG_ACCESS_TOKEN_EXPIRATION", Long.class);
        Long refreshTokenExpiration = environment.getProperty("ZENSPACE_CONFIG_REFRESH_TOKEN_EXPIRATION", Long.class);

        assert privateKey != null && accessTokenExpiration != null && refreshTokenExpiration != null;
        this.secret = privateKey;
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //  TODO: To get extra claims later configure it if needed.
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public String generateAccessToken(
            String username,
            Map<String, Object> extraClaims
    ) {
        return buildToken(extraClaims, username, accessTokenExpiration);
    }

    @Override
    public String generateRefreshToken(String username) {
        return buildToken(new HashMap<>(),username,refreshTokenExpiration);
    }

    @Override
    public Boolean isTokenValid(String token) {
        return extractExpiration(token).after(new Date());
    }

    @Override
    public Boolean isTokenValid(String token, String currentUser) {
        final String username = extractUsername(token);
        return (username.equals(currentUser)) && isTokenNonExpired(token);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            String username,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public Boolean isTokenNonExpired(String token) {
        return !extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}

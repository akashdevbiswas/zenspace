package com.cromxt.enterprisekart.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.cromxt.enterprisekart.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final String secret;
    private final Long expiration;

    private static final String AUTHORITIES = "authorities";

    @Override
    public UserDetails extractUserDetails(String token) {
        Claims claims = extractAllClaims(token);
        String userId = claims.getSubject();
        @SuppressWarnings("unchecked")
        List<String> authorityList = (List<String>) claims.get(AUTHORITIES, List.class);
        List<SimpleGrantedAuthority> authorities = authorityList.stream().map(SimpleGrantedAuthority::new).toList();
        return User.builder()
                .username(userId)
                .password("no_password")
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .build();
    }

    @Override
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public String generateToken(String userId, Collection<? extends GrantedAuthority> authorities,
            Map<String, Object> extraPayload) {
        Map<String, Object> extraClaims = new HashMap<>();
        List<String> authoritiesString = authorities.stream().map(GrantedAuthority::getAuthority).toList();
        extraClaims.put(AUTHORITIES, authoritiesString);

        if (Objects.isNull(extraPayload) || extraPayload.isEmpty()) {
            return createToken(userId, extraClaims);
        }
        extraPayload.keySet().forEach(eachKey -> extraClaims.put(eachKey, extraPayload.get(eachKey)));
        return createToken(userId, extraClaims);
    }

    public String generateSecret(String id) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        String data = String.format("%s-%s-%s", id, secret, System.currentTimeMillis());
        byte[] encodedHash = messageDigest.digest(data.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
        for (byte hash : encodedHash) {
            String hex = Integer.toHexString(0xff & hash);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private String createToken(String username, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
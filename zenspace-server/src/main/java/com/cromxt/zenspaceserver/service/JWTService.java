package com.cromxt.zenspaceserver.service;

import com.cromxt.zenspaceserver.entity.UserRole;

import java.util.Map;

public interface JWTService {

    String extractSubject(String token);
    String generateAccessToken(String username, Map<String, Object> extraClaims);
    String generateRefreshToken(String username);
    Boolean isTokenValid(String token);
    Boolean isTokenValid(String token, String username);
    Boolean isTokenNonExpired(String token);
    UserRole extractRole(String token);
}

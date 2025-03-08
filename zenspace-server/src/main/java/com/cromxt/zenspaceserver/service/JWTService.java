package com.cromxt.zenspaceserver.service;

import java.util.Map;

public interface JWTService {

    String extractUsername(String token);
    String generateAccessToken(String username, Map<String, Object> extraClaims);
    String generateRefreshToken(String username);
    Boolean isTokenValid(String token, String username);
    Boolean isTokenNonExpired(String token);
}

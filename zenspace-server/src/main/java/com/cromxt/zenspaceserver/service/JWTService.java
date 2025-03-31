package com.cromxt.zenspaceserver.service;

import com.cromxt.zenspaceserver.entity.PlatformPermissions;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface JWTService {

    String extractSubject(String token);
    String generateAccessToken(String username, Map<String, Object> extraClaims);
    String generateRefreshToken(String username);
    Boolean isTokenValid(String token);
    Boolean isTokenValid(String token, String username);
    Boolean isTokenNonExpired(String token);
    Set<PlatformPermissions> extractAuthorities(String token);
}

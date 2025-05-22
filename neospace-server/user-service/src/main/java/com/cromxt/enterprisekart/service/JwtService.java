package com.cromxt.enterprisekart.service;


import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    UserDetails extractUserDetails(String token);

    String generateToken(String userId, Collection<? extends GrantedAuthority> authorities, Map<String, Object> extraPayload);

    boolean isTokenExpired(String token);
}
package com.cromxt.enterprisekart.service;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface JwtService {

    UserDetails extractUserDetails(String token);

    String generateToken(String userId, Collection<? extends GrantedAuthority> authorities, Map<String, Object> extraPayload);

    boolean isTokenExpired(String token);
}
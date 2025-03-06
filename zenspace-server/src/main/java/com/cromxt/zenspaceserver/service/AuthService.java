package com.cromxt.zenspaceserver.service;

import com.cromxt.zenspaceserver.dtos.request.UserCredential;
import com.cromxt.zenspaceserver.dtos.response.AuthTokens;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<AuthTokens> generateToken(UserCredential credential);
    Mono<AuthTokens> generateAccessToken(String refreshToken);
}

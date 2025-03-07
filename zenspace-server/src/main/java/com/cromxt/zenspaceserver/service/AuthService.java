package com.cromxt.zenspaceserver.service;

import com.cromxt.zenspaceserver.dtos.request.UserCredential;
import com.cromxt.zenspaceserver.dtos.response.AuthTokens;

public interface AuthService {
    AuthTokens generateToken(UserCredential credential);
    AuthTokens generateAccessToken(String refreshToken);
}

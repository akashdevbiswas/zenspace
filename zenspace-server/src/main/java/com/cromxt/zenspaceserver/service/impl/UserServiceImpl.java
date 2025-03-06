package com.cromxt.zenspaceserver.service.impl;

import com.cromxt.zenspaceserver.dtos.request.NewUser;
import com.cromxt.zenspaceserver.dtos.request.UserCredential;
import com.cromxt.zenspaceserver.dtos.response.AuthTokens;
import com.cromxt.zenspaceserver.entity.UserEntity;
import com.cromxt.zenspaceserver.service.AuthService;
import com.cromxt.zenspaceserver.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class UserServiceImpl implements AuthService, UserService {


    @Override
    public Mono<AuthTokens> generateToken(UserCredential credential) {
        return null;
    }

    @Override
    public Mono<AuthTokens> generateAccessToken(String refreshToken) {
        return null;
    }

    @Override
    public Mono<UserEntity> saveUser(NewUser newUser) {
        return null;
    }
}

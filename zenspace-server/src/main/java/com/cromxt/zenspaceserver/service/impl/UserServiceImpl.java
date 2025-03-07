package com.cromxt.zenspaceserver.service.impl;

import com.cromxt.zenspaceserver.dtos.request.NewUser;
import com.cromxt.zenspaceserver.dtos.request.UserCredential;
import com.cromxt.zenspaceserver.dtos.response.AuthTokens;
import com.cromxt.zenspaceserver.entity.UserEntity;
import com.cromxt.zenspaceserver.service.AuthService;
import com.cromxt.zenspaceserver.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements AuthService, UserService {


    @Override
    public AuthTokens generateToken(UserCredential credential) {
        return null;
    }

    @Override
    public AuthTokens generateAccessToken(String refreshToken) {
        return null;
    }

    @Override
    public UserEntity saveUser(NewUser newUser) {
        return null;
    }
}

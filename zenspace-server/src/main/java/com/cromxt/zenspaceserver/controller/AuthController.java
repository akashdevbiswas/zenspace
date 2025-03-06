package com.cromxt.zenspaceserver.controller;


import com.cromxt.zenspaceserver.dtos.request.NewUser;
import com.cromxt.zenspaceserver.dtos.request.UserCredential;
import com.cromxt.zenspaceserver.dtos.response.AuthTokens;
import com.cromxt.zenspaceserver.dtos.response.UserResponse;
import com.cromxt.zenspaceserver.entity.UserEntity;
import com.cromxt.zenspaceserver.service.AuthService;
import com.cromxt.zenspaceserver.service.EntityMapper;
import com.cromxt.zenspaceserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    private final EntityMapper entityMapper;


    @PostMapping
    public Mono<AuthTokens> loginUser(UserCredential credential) {
        return authService.generateToken(credential);
    }
    @PostMapping("/refresh")
    public Mono<AuthTokens> refreshToken(String refreshToken) {
        return authService.generateAccessToken(refreshToken);
    }
    @PostMapping("/logout")
    public Mono<AuthTokens> logoutUser(String refreshToken) {
        return Mono.empty();
    }
    @PostMapping("/register")
    public Mono<UserResponse> registerUser(NewUser newUser) {
        Mono<UserEntity> userEntity = userService.saveUser(newUser);
        return userEntity.flatMap(entityMapper::getUserResponseFromUserEntity);
    }


}

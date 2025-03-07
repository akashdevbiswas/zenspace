package com.cromxt.zenspaceserver.controller;


import com.cromxt.zenspaceserver.dtos.request.NewUser;
import com.cromxt.zenspaceserver.dtos.request.UserCredential;
import com.cromxt.zenspaceserver.dtos.response.AuthTokens;
import com.cromxt.zenspaceserver.dtos.response.UserResponse;
import com.cromxt.zenspaceserver.service.AuthService;
import com.cromxt.zenspaceserver.service.EntityMapper;
import com.cromxt.zenspaceserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    private final EntityMapper entityMapper;


    @PostMapping
    public AuthTokens loginUser(UserCredential credential) {
        return authService.generateToken(credential);
    }
    @PostMapping("/refresh")
    public AuthTokens refreshToken(String refreshToken) {
        return authService.generateAccessToken(refreshToken);
    }
    @PostMapping("/logout")
    public AuthTokens logoutUser(String refreshToken) {
       return new AuthTokens("", "");
    }
    @PostMapping("/register")
    public UserResponse registerUser(NewUser newUser) {
        return null;
    }

}

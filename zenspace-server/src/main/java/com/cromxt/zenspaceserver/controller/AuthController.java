package com.cromxt.zenspaceserver.controller;


import com.cromxt.zenspaceserver.dtos.request.NewUserRequest;
import com.cromxt.zenspaceserver.dtos.request.UserCredential;
import com.cromxt.zenspaceserver.dtos.response.AuthTokens;
import com.cromxt.zenspaceserver.service.AuthService;
import com.cromxt.zenspaceserver.service.MediaObjectsService;
import com.cromxt.zenspaceserver.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final MediaObjectsService mediaObjectsService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthTokens loginUser(@RequestBody UserCredential credential,HttpServletResponse response) {
        AuthTokens tokens = authService.generateToken(credential);
        Cookie cookie = new Cookie("refreshToken", tokens.refreshToken());
        cookie.setHttpOnly(true);
        cookie.setPath("/api/v1/auth/refresh");
        response.addCookie(cookie);
        return tokens;
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthTokens refreshAccessToken(@RequestAttribute("refreshToken") String refreshToken) {
        return authService.generateAccessToken(refreshToken);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logoutUser(HttpServletResponse response) {
        Cookie cookie = new Cookie("refreshToken", "");
        response.addCookie(cookie);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void registerUser(@RequestBody NewUserRequest newUserRequest) {
        userService.saveUser(newUserRequest);
    }

    @GetMapping("/avatars")
    public List<String> getAllAvailableAvatars(){
        return mediaObjectsService.getAllAvailableAvatars();
    }

}

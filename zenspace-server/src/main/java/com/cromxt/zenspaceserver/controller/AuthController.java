package com.cromxt.zenspaceserver.controller;


import com.cromxt.zenspaceserver.dtos.request.NewUserRequest;
import com.cromxt.zenspaceserver.dtos.request.UserCredential;
import com.cromxt.zenspaceserver.dtos.response.AuthTokens;
import com.cromxt.zenspaceserver.service.*;
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

    private final UserService userService;
    private final AuthService authService;
    private final EntityMapper entityMapper;
    private final MediaObjectService mediaObjectService;


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
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@ModelAttribute NewUserRequest newUserRequest) {
//        TODO:Implement the logic to create a user.
    }

    @GetMapping("/avatars")
    public List<String> getAllAvailableAvatars(){
        return mediaObjectService.getAllAvailableAvatars();
    }

}

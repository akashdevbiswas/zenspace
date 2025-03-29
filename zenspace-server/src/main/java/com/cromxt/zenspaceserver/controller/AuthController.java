package com.cromxt.zenspaceserver.controller;


import com.cromxt.zenspaceserver.dtos.request.NewUser;
import com.cromxt.zenspaceserver.dtos.request.UserCredential;
import com.cromxt.zenspaceserver.dtos.response.AuthTokens;
import com.cromxt.zenspaceserver.dtos.response.UserResponse;
import com.cromxt.zenspaceserver.service.AuthService;
import com.cromxt.zenspaceserver.service.EntityMapper;
import com.cromxt.zenspaceserver.service.UserService;
import com.cromxt.zenspaceserver.service.UtilService;
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
    private final UtilService utilService;
    private final EntityMapper entityMapper;


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
    public UserResponse registerUser(@ModelAttribute NewUser newUser) {
        return entityMapper.getUserResponseFromUserEntity(userService.saveUser(newUser));
    }

    @GetMapping("/avatars")
    public List<String> getAllAvailableAvatars(){
        return utilService.getAllAvailableAvatars();
    }

}

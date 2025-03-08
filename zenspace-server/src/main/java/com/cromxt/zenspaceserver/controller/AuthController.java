package com.cromxt.zenspaceserver.controller;


import com.cromxt.zenspaceserver.dtos.request.NewUser;
import com.cromxt.zenspaceserver.dtos.request.UserCredential;
import com.cromxt.zenspaceserver.dtos.response.AuthTokens;
import com.cromxt.zenspaceserver.dtos.response.UserResponse;
import com.cromxt.zenspaceserver.service.AuthService;
import com.cromxt.zenspaceserver.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthTokens loginUser(@RequestBody UserCredential credential,HttpServletResponse response) {
        AuthTokens tokens = authService.generateToken(credential);
        Cookie cookie = new Cookie("refreshToken", tokens.refreshToken());
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
        System.out.println(newUser);
        return userService.saveUser(newUser);
    }

}

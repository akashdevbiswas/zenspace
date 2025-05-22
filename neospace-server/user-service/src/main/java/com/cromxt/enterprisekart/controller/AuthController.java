package com.cromxt.enterprisekart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cromxt.enterprisekart.dtos.requests.UserRequest;
import com.cromxt.enterprisekart.service.AuthService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;


  @PostMapping(value = "/register")
  @ResponseStatus(value = HttpStatus.CREATED)
  public void registerUser(@RequestBody UserRequest userRequest){
    System.out.println(userRequest);
    authService.saveUser(userRequest);
  }
}

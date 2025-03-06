package com.cromxt.zenspaceserver.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    @GetMapping
    public Mono<String> greet(){
        return Mono.just("Hello World!");
    }
}

package com.cromxt.zenspaceserver.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@PreAuthorize("hasRole('AKASH')")
public class DemoController {

    @GetMapping
    public String hello() {
        return "hello";
    }
}

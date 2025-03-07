package com.cromxt.zenspaceserver.service.impl;

import com.cromxt.zenspaceserver.service.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class JWTServiceImplTest {

    @Autowired
    private Environment environment;


    @Test
    void createdTokenWithAParticularUsernameAlwaysReturnsTheSameUsername(){
        JWTService jwtService = new JWTServiceImpl(environment);


        String token = jwtService.generateAccessToken("username", new HashMap<>());
        String username = jwtService.extractUsername(token);

        System.out.println(token);

        assertEquals("username",username);
    }

    @Test
    void demo(){
        UserDetails user = User.builder().username("username").password("password").build();
        assertNotNull(user);
    }

}
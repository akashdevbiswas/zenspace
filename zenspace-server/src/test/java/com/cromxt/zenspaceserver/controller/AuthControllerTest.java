package com.cromxt.zenspaceserver.controller;

import com.cromxt.zenspaceserver.auth.JwtAuthenticationFilter;
import com.cromxt.zenspaceserver.config.SecurityConfig;
import com.cromxt.zenspaceserver.dtos.request.NewUserRequest;
import com.cromxt.zenspaceserver.service.AuthService;
import com.cromxt.zenspaceserver.service.JWTService;
import com.cromxt.zenspaceserver.service.MediaObjectService;
import com.cromxt.zenspaceserver.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AuthController.class)
@Import(SecurityConfig.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private JWTService jwtService;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private MediaObjectService mediaObjectService;

    @MockitoBean
    private AuthenticationProvider authenticationProvider;

    @MockitoBean
    private UserService userService;

    private NewUserRequest newUserRequest;


    @BeforeEach
    void setUp() {
        this.newUserRequest = new NewUserRequest(
            "username",
            "password",
            "abc@gmail.com"
        );
    }

    @Test
    void createAUserFromUserRequest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonData = mapper.writeValueAsString(this.newUserRequest);
        System.out.println(jsonData);
        mvc.perform(
                post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData)
        ).andExpect(status().isNoContent());
    }


}
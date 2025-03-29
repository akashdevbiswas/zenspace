package com.cromxt.zenspaceserver.controller;


import com.cromxt.zenspaceserver.dtos.response.UserResponse;
import com.cromxt.zenspaceserver.entity.UserEntity;
import com.cromxt.zenspaceserver.service.EntityMapper;
import com.cromxt.zenspaceserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EntityMapper entityMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@RequestAttribute String userId) {
        UserEntity userEntity = userService.getUserById(userId);
        return entityMapper.getUserResponseFromUserEntity(userEntity);
    }
}

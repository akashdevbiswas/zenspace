package com.cromxt.zenspaceserver.service;

import com.cromxt.zenspaceserver.dtos.request.NewUser;
import com.cromxt.zenspaceserver.dtos.response.UserResponse;
import com.cromxt.zenspaceserver.entity.UserEntity;

public interface UserService {

    UserEntity saveUser(NewUser newUser);

    UserEntity getUserById(String userId);
}

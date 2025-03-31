package com.cromxt.zenspaceserver.service;

import com.cromxt.zenspaceserver.dtos.request.NewUserRequest;
import com.cromxt.zenspaceserver.entity.UserEntity;

public interface UserService {

    UserEntity saveUser(NewUserRequest newUserRequest);

    UserEntity getUserById(String userId);
}

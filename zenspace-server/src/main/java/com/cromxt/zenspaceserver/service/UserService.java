package com.cromxt.zenspaceserver.service;

import com.cromxt.zenspaceserver.dtos.request.UserRequest;
import com.cromxt.zenspaceserver.entity.UserEntity;

public interface UserService {

    UserEntity saveUser(UserRequest userRequest);

    UserEntity getUserById(String userId);
}

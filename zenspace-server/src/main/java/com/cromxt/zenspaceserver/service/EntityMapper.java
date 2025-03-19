package com.cromxt.zenspaceserver.service;

import com.cromxt.zenspaceserver.dtos.request.NewUser;
import com.cromxt.zenspaceserver.dtos.response.UserResponse;
import com.cromxt.zenspaceserver.entity.UserEntity;

import java.util.List;

public interface EntityMapper {
    UserResponse getUserResponseFromUserEntity(UserEntity userEntity);
    UserEntity getUserEntityFromNewUser(NewUser newUser);
}

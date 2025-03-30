package com.cromxt.zenspaceserver.service;

import com.cromxt.zenspaceserver.dtos.request.UserRequest;
import com.cromxt.zenspaceserver.dtos.response.UserResponse;
import com.cromxt.zenspaceserver.entity.UserEntity;

public interface EntityMapper {
    UserResponse getUserResponseFromUserEntity(UserEntity userEntity);
    UserEntity getUserEntityFromUserRequest(UserRequest userRequest);
}

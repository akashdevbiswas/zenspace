package com.cromxt.zenspaceserver.service;

import com.cromxt.zenspaceserver.dtos.request.NewUser;
import com.cromxt.zenspaceserver.dtos.response.UserResponse;
import com.cromxt.zenspaceserver.entity.UserEntity;
import reactor.core.publisher.Mono;

public interface EntityMapper {
    Mono<UserResponse> getUserResponseFromUserEntity(UserEntity userEntity);
    Mono<UserEntity> getUserEntityFromNewUser(NewUser newUser);
}

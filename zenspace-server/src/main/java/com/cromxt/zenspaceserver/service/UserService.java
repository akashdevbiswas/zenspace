package com.cromxt.zenspaceserver.service;

import com.cromxt.zenspaceserver.dtos.request.NewUser;
import com.cromxt.zenspaceserver.entity.UserEntity;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserEntity> saveUser(NewUser newUser);
}

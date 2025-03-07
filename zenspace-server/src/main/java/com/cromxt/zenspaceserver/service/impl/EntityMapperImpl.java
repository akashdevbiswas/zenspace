package com.cromxt.zenspaceserver.service.impl;


import com.cromxt.zenspaceserver.dtos.request.NewUser;
import com.cromxt.zenspaceserver.dtos.response.UserResponse;
import com.cromxt.zenspaceserver.entity.UserEntity;
import com.cromxt.zenspaceserver.service.EntityMapper;
import org.springframework.stereotype.Service;

@Service
public class EntityMapperImpl implements EntityMapper {

    @Override
    public UserResponse getUserResponseFromUserEntity(UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity getUserEntityFromNewUser(NewUser newUser) {
        return null;
    }
}

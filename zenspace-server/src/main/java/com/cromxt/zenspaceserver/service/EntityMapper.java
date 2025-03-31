package com.cromxt.zenspaceserver.service;

import com.cromxt.zenspaceserver.dtos.request.NewUserRequest;
import com.cromxt.zenspaceserver.dtos.response.UserProfileResponse;
import com.cromxt.zenspaceserver.entity.UserEntity;
import com.cromxt.zenspaceserver.entity.UserProfile;

public interface EntityMapper {
    UserProfileResponse getUserResponseFromUserEntity();

    UserEntity getUserEntityFromUserRequest(NewUserRequest newUserRequest);
}

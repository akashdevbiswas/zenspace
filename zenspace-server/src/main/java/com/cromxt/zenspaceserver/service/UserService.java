package com.cromxt.zenspaceserver.service;

import com.cromxt.zenspaceserver.dtos.request.NewUserRequest;
import com.cromxt.zenspaceserver.dtos.request.UserDataRequest;
import com.cromxt.zenspaceserver.dtos.response.UserProfileResponse;

public interface UserService {

    void saveUser(NewUserRequest newUserRequest);

    UserProfileResponse getProfileFromUserId(String userId);

    UserProfileResponse updateProfile(String userId, UserDataRequest userDataRequest);

    Boolean isProfileComplete(String userId);
}

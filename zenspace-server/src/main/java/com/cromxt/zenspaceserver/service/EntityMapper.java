package com.cromxt.zenspaceserver.service;

import com.cromxt.zenspaceserver.dtos.request.NewUserRequest;
import com.cromxt.zenspaceserver.dtos.request.SpaceRequest;
import com.cromxt.zenspaceserver.dtos.request.UserDataRequest;
import com.cromxt.zenspaceserver.dtos.response.SpacePageableResponse;
import com.cromxt.zenspaceserver.dtos.response.SpaceResponse;
import com.cromxt.zenspaceserver.dtos.response.UserProfileResponse;
import com.cromxt.zenspaceserver.entity.*;

import java.util.List;

public interface EntityMapper {

    UserProfileResponse createUserProfileResponseFromUserProfile(UserProfile userProfile);

    UserEntity getUserEntityFromUserRequest(NewUserRequest newUserRequest);

    UserProfile getUserProfileFromUpdateUserdata(UserDataRequest userDataRequest);

    Space createSpaceFromSpaceRequest(SpaceRequest spaceRequest, MediaObjects mediaObject);

    SpaceUserMembers createSpaceUserMembers(Space space, UserEntity user, SpaceRules rule);

    SpaceResponse getSpaceResponseFromSpaceEntity(Space space, SpaceRules rules);

    SpacePageableResponse createSpacePageableResponse(List<SpaceResponse> spaceResponseList, Integer pageNumber, Integer pageSize, Boolean isLast);

    UserProfile createUserProfileFromUserData(String userId, UserDataRequest userDataRequest, MediaObjects mediaObjects);
}

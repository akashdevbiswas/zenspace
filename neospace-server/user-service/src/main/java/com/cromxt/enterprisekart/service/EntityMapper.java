package com.cromxt.enterprisekart.service;

import com.cromxt.enterprisekart.dtos.db.UserProfileDTO;
import com.cromxt.enterprisekart.dtos.requests.UserProfileRequest;
import com.cromxt.enterprisekart.dtos.requests.UserRequest;
import com.cromxt.enterprisekart.dtos.responses.UserProfileResponse;
import com.cromxt.enterprisekart.models.UserModel;
import com.cromxt.enterprisekart.models.UserProfile;

public interface EntityMapper {
  UserModel buildUserModel(UserRequest userRequest);

  UserProfileResponse buildUserProfileResponse(UserProfileDTO user);

  UserProfile buildNewUserProfile(String userId, UserProfileRequest userProfileRequest);
}

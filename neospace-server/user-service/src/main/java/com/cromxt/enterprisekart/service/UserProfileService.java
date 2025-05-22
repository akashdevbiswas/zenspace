package com.cromxt.enterprisekart.service;

import com.cromxt.enterprisekart.dtos.requests.UserProfileRequest;
import com.cromxt.enterprisekart.dtos.responses.UserProfileResponse;

public interface UserProfileService {

  Boolean isUserProfileComplete(String userId);

  UserProfileResponse getProfile(String userId);

  UserProfileResponse updateProfile(String userId, UserProfileRequest userProfileRequest);
  
}

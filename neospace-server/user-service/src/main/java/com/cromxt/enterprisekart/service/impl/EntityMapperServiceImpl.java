package com.cromxt.enterprisekart.service.impl;

import java.time.LocalDate;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.cromxt.enterprisekart.dtos.db.UserProfileDTO;
import com.cromxt.enterprisekart.dtos.requests.UserProfileRequest;
import com.cromxt.enterprisekart.dtos.requests.UserRequest;
import com.cromxt.enterprisekart.dtos.responses.UserProfileResponse;
import com.cromxt.enterprisekart.models.UserModel;
import com.cromxt.enterprisekart.models.UserProfile;
import com.cromxt.enterprisekart.service.EntityMapper;

@Service
public class EntityMapperServiceImpl implements EntityMapper {

  @Override
  public UserModel buildUserModel(UserRequest userRequest) {
    return UserModel.builder()
        .username(userRequest.username())
        .password(userRequest.password())
        .role(userRequest.role())
        .email(userRequest.email())
        .dateOfBirth(LocalDate.parse(userRequest.dateOfBirth()))
        .build();
  }

  public UserProfileResponse buildUserProfileResponse(UserProfileDTO user) {
    
    return new UserProfileResponse(
        user.getId(),
        user.getUsername(),
        user.getFirstName(),
        user.getLastName(),
        user.getDescription(),
        user.getProfileImage());
  }

  @Override
  public UserProfile buildNewUserProfile(String userId, UserProfileRequest userProfileRequest) {
    String imageUrl = ""; 
    return UserProfile.builder()
    .id(userId)
    .description(userProfileRequest.description())
    .firstName(userProfileRequest.firstName())
    .lastName(userProfileRequest.lastName())
    .gender(userProfileRequest.gender())
    .profileImage(imageUrl)
    .build();
  }

}

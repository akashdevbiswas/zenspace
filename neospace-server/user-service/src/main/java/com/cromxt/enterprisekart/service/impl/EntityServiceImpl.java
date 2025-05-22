package com.cromxt.enterprisekart.service.impl;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.cromxt.enterprisekart.dtos.db.UserProfileDTO;
import com.cromxt.enterprisekart.dtos.requests.SpaceRequest;
import com.cromxt.enterprisekart.dtos.requests.UserCreadentials;
import com.cromxt.enterprisekart.dtos.requests.UserProfileRequest;
import com.cromxt.enterprisekart.dtos.requests.UserRequest;
import com.cromxt.enterprisekart.dtos.responses.AuthToken;
import com.cromxt.enterprisekart.dtos.responses.UserProfileResponse;
import com.cromxt.enterprisekart.exceptions.UserNotFoundException;
import com.cromxt.enterprisekart.models.UserModel;
import com.cromxt.enterprisekart.models.UserProfile;
import com.cromxt.enterprisekart.repository.UserProfileRepository;
import com.cromxt.enterprisekart.repository.UserRepository;
import com.cromxt.enterprisekart.service.AuthService;
import com.cromxt.enterprisekart.service.EntityMapper;
import com.cromxt.enterprisekart.service.JwtService;
import com.cromxt.enterprisekart.service.SpaceService;
import com.cromxt.enterprisekart.service.UserProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntityServiceImpl implements AuthService, SpaceService, UserProfileService {

  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final EntityMapper entityMapper;
  private final AuthenticationManager authenticationManager;
  private final UserProfileRepository userProfileRepository;

  @Override
  public void saveUser(UserRequest userRequest) {
    UserModel userModel = entityMapper.buildUserModel(userRequest);
    userRepository.save(userModel);
  }

  @Override
  public AuthToken generateToken(UserCreadentials userCreadentials) {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        userCreadentials.username(), userCreadentials.password());

    Authentication authentication = authenticationManager.authenticate(authenticationToken);

    UserModel user = (UserModel) authentication.getPrincipal();

    String token = jwtService.generateToken(user.getId(), user.getAuthorities(), new HashMap<>());
    return new AuthToken(token);
  }

  @Override
  public void saveSpace(SpaceRequest spaceRequest) {

  }

  @Override
  public Boolean isUserProfileComplete(String userId) {
    return userProfileRepository.existsById(userId);
  }

  @Override
  public UserProfileResponse getProfile(String userId) {
    Optional<UserProfileDTO> userProfileDTOptional = userProfileRepository.getUserProfileDTO(userId);

    UserProfileDTO user = userProfileDTOptional
        .orElseThrow(() -> new UserNotFoundException("User profile not found with id: " + userId));
    return entityMapper.buildUserProfileResponse(user);
  }

  @Override
  public UserProfileResponse updateProfile(String userId, UserProfileRequest userProfileRequest) {
    Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userId);

    if (userProfileOptional.isEmpty()) {
      UserProfile userProfile = entityMapper.buildNewUserProfile(userId, userProfileRequest);
      userProfileRepository.save(userProfile);
      UserProfileDTO userProfileDTO = userProfileRepository.getUserProfileDTO(userId)
          .orElseThrow(() -> new UserNotFoundException("User profile not found with id: " + userId));
      return entityMapper.buildUserProfileResponse(userProfileDTO);
    }
    
    UserProfile userProfile = userProfileOptional.get();

    if (Objects.nonNull(userProfileRequest.firstName())
        && Objects.nonNull(userProfileRequest.lastName())
        && Objects.nonNull(userProfileRequest.description())
        && Objects.nonNull(userProfileRequest.gender())) {

      userProfile.setFirstName(userProfileRequest.firstName());
      userProfile.setLastName(userProfileRequest.lastName());
      userProfile.setDescription(userProfileRequest.description());
      userProfile.setGender(userProfileRequest.gender());

      userProfileRepository.save(userProfile);

    }

    UserProfileDTO userProfileDTO = userProfileRepository.getUserProfileDTO(userId)
        .orElseThrow(() -> new UserNotFoundException("User profile not found with id: " + userId));

    return entityMapper.buildUserProfileResponse(userProfileDTO);
  }

}

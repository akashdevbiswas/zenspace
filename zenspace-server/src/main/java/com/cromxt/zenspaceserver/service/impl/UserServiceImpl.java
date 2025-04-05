package com.cromxt.zenspaceserver.service.impl;

import com.cromxt.zenspaceserver.dtos.request.NewUserRequest;
import com.cromxt.zenspaceserver.dtos.request.UserDataRequest;
import com.cromxt.zenspaceserver.dtos.request.UserCredential;
import com.cromxt.zenspaceserver.dtos.response.AuthTokens;
import com.cromxt.zenspaceserver.dtos.response.UserProfileResponse;
import com.cromxt.zenspaceserver.entity.MediaObjects;
import com.cromxt.zenspaceserver.entity.UserEntity;
import com.cromxt.zenspaceserver.entity.UserProfile;
import com.cromxt.zenspaceserver.entity.UserRole;
import com.cromxt.zenspaceserver.exceptions.UserNotFoundException;
import com.cromxt.zenspaceserver.respository.UserProfileRepository;
import com.cromxt.zenspaceserver.respository.UserRepository;
import com.cromxt.zenspaceserver.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements AuthService, UserService {

    private final UserRepository userRepository;
    private final EntityMapper entityMapper;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserProfileRepository userProfileRepository;
    private final MediaObjectsService mediaObjectsService;

    @Override
    public AuthTokens generateToken(UserCredential credential) {

        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
                credential.usernameOrEmail(),
                credential.password()
        );

        Authentication userAuthentication = authenticationManager.authenticate(userToken);

        UserEntity user = (UserEntity) userAuthentication.getPrincipal();

        Map<String, Object> extraClaims = new HashMap<>();
        UserRole userRole = user.getUserRole();
        List<String> authorities = userRole.getPermissions().stream().map(Enum::name).toList();

        extraClaims.put("authorities", authorities);
        extraClaims.put("role", userRole.getRoleName());

        String accessToken = jwtService.generateAccessToken(user.getId(), extraClaims);
        String refreshToken = jwtService.generateRefreshToken(user.getId());
        return new AuthTokens(accessToken, refreshToken);
    }

    @Override
    public AuthTokens generateAccessToken(String refreshToken) {
        String userId = jwtService.extractSubject(refreshToken);

        Optional<UserEntity> savedUserOptional = userRepository.findById(userId);

        UserEntity savedUser = savedUserOptional.orElseThrow(() -> new UserNotFoundException("User not found"));

        String newAccessToken = jwtService.generateAccessToken(savedUser.getId(), new HashMap<>());
        return new AuthTokens(newAccessToken, refreshToken);
    }

    @Override
    public void saveUser(NewUserRequest newUserRequest) {
        UserEntity userEntity = entityMapper.getUserEntityFromUserRequest(newUserRequest);
        userRepository.save(userEntity);
    }

    @Override
    public UserProfileResponse getProfileFromUserId(String userId) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userId);
        UserProfile userProfile = userProfileOptional.orElseThrow(() -> new UserNotFoundException("User profile not found"));
        return entityMapper.createUserProfileResponseFromUserProfile(userProfile);
    }

    @Override
    public UserProfileResponse updateProfile(String userId, UserDataRequest userDataRequest) {

        MediaObjects mediaObjects = null;
        if (Objects.nonNull(userDataRequest.profileImage())) {
            mediaObjects = mediaObjectsService.saveMediaObject(userDataRequest.profileImage());
        }

        UserProfile userProfile = entityMapper.createUserProfileFromUserData(userId, userDataRequest, mediaObjects);
        UserProfile savedProfile = userProfileRepository.save(userProfile);

        return entityMapper.createUserProfileResponseFromUserProfile(savedProfile);
    }

    @Override
    public Boolean isProfileComplete(String userId) {
        return userProfileRepository.existsById(userId);
    }

}

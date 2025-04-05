package com.cromxt.zenspaceserver.service.impl;


import com.cromxt.toolkit.crombucket.clients.CromBucketWebClient;
import com.cromxt.zenspaceserver.dtos.request.NewUserRequest;
import com.cromxt.zenspaceserver.dtos.request.SpaceRequest;
import com.cromxt.zenspaceserver.dtos.request.UserDataRequest;
import com.cromxt.zenspaceserver.dtos.response.SpacePageableResponse;
import com.cromxt.zenspaceserver.dtos.response.SpaceResponse;
import com.cromxt.zenspaceserver.dtos.response.UserProfileResponse;
import com.cromxt.zenspaceserver.entity.*;
import com.cromxt.zenspaceserver.service.EntityMapper;
import com.cromxt.zenspaceserver.service.MediaObjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntityMapperImpl implements EntityMapper {

    private final PasswordEncoder passwordEncoder;
    private final CromBucketWebClient cromBucketWebClient;
    private final UserRole userRole;
    private final MediaObjectsService mediaObjectsService;

    @Override
    public UserProfileResponse createUserProfileResponseFromUserProfile(UserProfile userProfile) {
        UserEntity user = userProfile.getUser();
        return UserProfileResponse.builder()
                .id(userProfile.getId())
                .username(user.getUsername())
                .firstName(userProfile.getFirstName())
                .lastName(userProfile.getLastName())
                .profileImage(userProfile.getMediaObjects().getUrl())
                .build();
    }


    @Override
    public UserEntity getUserEntityFromUserRequest(NewUserRequest newUserRequest) {
        return UserEntity.builder()
                .username(newUserRequest.username())
                .userRole(userRole)
                .password(passwordEncoder.encode(newUserRequest.password()))
                .email(newUserRequest.email())
                .build();
    }

    @Override
    public UserProfile getUserProfileFromUpdateUserdata(UserDataRequest userDataRequest) {

//
//        UserEntity.UserEntityBuilder userEntityBuilder = UserEntity.builder()
//                .username(newUserRequest.username())
//                .password(encodedPassword)
//                .email(newUserRequest.email())
//                .firstName(newUserRequest.firstName())
//                .lastName(newUserRequest.lastName())
//                .dateOfBirth(LocalDate.parse(newUserRequest.dateOfBirth()))
//                .gender(Gender.valueOf(newUserRequest.gender().toUpperCase()));
//        if(fileResponse==null){
//            return userEntityBuilder
//                    .avatarUrl(profileImageUrl)
//                    .build();
//        }
//        return userEntityBuilder
//                .avatarUrl(profileImageUrl)
//                .mediaId(fileResponse.getMediaId())
//                .build();
        return null;
    }

    @Override
    public Space createSpaceFromSpaceRequest(SpaceRequest spaceRequest, MediaObjects mediaObject) {
        return Space.builder()
                .name(spaceRequest.name())
                .description(spaceRequest.description())
                .mediaObjects(mediaObject)
                .build();
    }

    @Override
    public SpaceUserMembers createSpaceUserMembers(Space space, UserEntity user, SpaceRules rule) {
        SpaceUserMembersKey primaryKey = SpaceUserMembersKey.builder()
                .user(user)
                .space(space)
                .rule(rule)
                .build();

        return SpaceUserMembers.builder()
                .id(primaryKey)
                .build();
    }


    @Override
    public SpaceResponse getSpaceResponseFromSpaceEntity(Space space, SpaceRules rules) {
        return SpaceResponse.builder()
                .id(space.getId())
                .name(space.getName())
                .description(space.getDescription())
                .createdAt(space.getCreatedOn())
                .spaceProfileImage(rules.getId())
                .ruleId(rules.getId())
                .build();
    }

    @Override
    public SpacePageableResponse createSpacePageableResponse(List<SpaceResponse> spaceResponseList,
                                                             Integer pageNumber,
                                                             Integer pageSize,
                                                             Boolean isLast) {
        return SpacePageableResponse
                .builder()
                .pageSize(pageSize)
                .pageNumber(pageNumber)
                .last(isLast)
                .spaceResponses(spaceResponseList)
                .build();
    }

    @Override
    public UserProfile createUserProfileFromUserData(String userId,
                                                     UserDataRequest userDataRequest,
                                                     MediaObjects mediaObjects) {

        UserProfile.UserProfileBuilder userProfileBuilder = UserProfile.builder()
                .user(UserEntity.builder()
                        .id(userId)
                        .build())
                .bio(userDataRequest.bio())
                .dateOfBirth(userDataRequest.dateOfBirth())
                .firstName(userDataRequest.firstName())
                .lastName(userDataRequest.lastName())
                .gender(userDataRequest.gender())
                .avatar(mediaObjectsService.getRandomAvatar());

        if(mediaObjects==null){
            return userProfileBuilder.build();
        }

        return userProfileBuilder
                .mediaObjects(mediaObjects)
                .build();
    }


}

package com.cromxt.zenspaceserver.service.impl;


import com.cromxt.toolkit.crombucket.clients.CromBucketWebClient;
import com.cromxt.toolkit.crombucket.response.FileResponse;
import com.cromxt.zenspaceserver.dtos.request.NewUserRequest;
import com.cromxt.zenspaceserver.dtos.request.UpdatedUserData;
import com.cromxt.zenspaceserver.dtos.response.UserProfileResponse;
import com.cromxt.zenspaceserver.entity.Gender;
import com.cromxt.zenspaceserver.entity.UserEntity;
import com.cromxt.zenspaceserver.entity.UserProfile;
import com.cromxt.zenspaceserver.entity.UserRole;
import com.cromxt.zenspaceserver.exceptions.CromBucketClientException;
import com.cromxt.zenspaceserver.service.EntityMapper;
import com.cromxt.zenspaceserver.service.MediaObjectService;
import com.cromxt.zenspaceserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EntityMapperImpl implements EntityMapper {

    private final PasswordEncoder passwordEncoder;
    private final CromBucketWebClient cromBucketWebClient;
    private final MediaObjectService mediaObjectService;
    private final UserRole userRole;


    @Override
    public UserProfileResponse getUserResponseFromUserEntity() {

        return null;
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
    public UserProfile getUserProfileFromUpdateUserdata(UpdatedUserData updatedUserData) {
        //        String encodedPassword = passwordEncoder.encode(newUserRequest.password());
//        FileResponse fileResponse=null;
//        String profileImageUrl = null;
//        if(newUserRequest.profileImage() == null && newUserRequest.avatar() == null) {
//            profileImageUrl = utilService.getDefaultAvatarUrl();
//        }else if(newUserRequest.avatar() != null) {
//            profileImageUrl = utilService.getAvatarUrlByIndex(newUserRequest.avatar());
//        }else{
//            try {
//                fileResponse = cromBucketWebClient.saveFile(newUserRequest.profileImage());
//                profileImageUrl = fileResponse.getAccessUrl();
//            } catch (IOException e) {
//                throw new CromBucketClientException("Failed to save the file with message: " + e.getMessage());
//            }
//        }
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
}

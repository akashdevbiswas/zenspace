package com.cromxt.zenspaceserver.service.impl;


import com.cromxt.toolkit.crombucket.clients.CromBucketWebClient;
import com.cromxt.toolkit.crombucket.response.FileResponse;
import com.cromxt.zenspaceserver.dtos.request.NewUser;
import com.cromxt.zenspaceserver.dtos.response.UserResponse;
import com.cromxt.zenspaceserver.entity.Gender;
import com.cromxt.zenspaceserver.entity.UserEntity;
import com.cromxt.zenspaceserver.exceptions.CromBucketClientException;
import com.cromxt.zenspaceserver.service.EntityMapper;
import com.cromxt.zenspaceserver.service.UtilService;
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
    private final UtilService utilService;

    @Override
    public UserResponse getUserResponseFromUserEntity(UserEntity userEntity) {
        return new UserResponse(
                userEntity.getId().toString(),
                userEntity.getUsername(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getAvatarUrl(),
                userEntity.getDateOfBirth().toString(),
                userEntity.getGender().name()
        );
    }

    @Override
    public UserEntity getUserEntityFromNewUser(NewUser newUser) {
        String encodedPassword = passwordEncoder.encode(newUser.password());
        FileResponse fileResponse=null;
        String profileImageUrl = null;
        if(newUser.profileImage() == null && newUser.avatar() == null) {
            profileImageUrl = utilService.getDefaultAvatarUrl();
        }else if(newUser.avatar() != null) {
            profileImageUrl = utilService.getAvatarUrlByIndex(newUser.avatar());
        }else{
            try {
                fileResponse = cromBucketWebClient.saveFile(newUser.profileImage());
                profileImageUrl = fileResponse.getAccessUrl();
            } catch (IOException e) {
                throw new CromBucketClientException("Failed to save the file with message: " + e.getMessage());
            }
        }

        UserEntity.UserEntityBuilder userEntityBuilder = UserEntity.builder()
                .username(newUser.username())
                .password(encodedPassword)
                .email(newUser.email())
                .firstName(newUser.firstName())
                .lastName(newUser.lastName())
                .dateOfBirth(LocalDate.parse(newUser.dateOfBirth()))
                .gender(Gender.valueOf(newUser.gender().toUpperCase()));
        if(fileResponse==null){
            return userEntityBuilder
                    .avatarUrl(profileImageUrl)
                    .build();
        }
        return userEntityBuilder
                .avatarUrl(profileImageUrl)
                .mediaId(fileResponse.getMediaId())
                .build();
    }
}

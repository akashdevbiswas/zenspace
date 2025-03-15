package com.cromxt.zenspaceserver.service.impl;


import com.cromxt.toolkit.crombucket.clients.CromBucketWebClient;
import com.cromxt.toolkit.crombucket.response.FileResponse;
import com.cromxt.zenspaceserver.dtos.request.NewUser;
import com.cromxt.zenspaceserver.dtos.response.UserResponse;
import com.cromxt.zenspaceserver.entity.Gender;
import com.cromxt.zenspaceserver.entity.UserEntity;
import com.cromxt.zenspaceserver.exceptions.CromBucketClientException;
import com.cromxt.zenspaceserver.service.EntityMapper;
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

    @Override
    public UserResponse getUserResponseFromUserEntity(UserEntity userEntity) {
        return new UserResponse(
                userEntity.getId().toString(),
                userEntity.getUsername(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getAvatar(),
                userEntity.getDateOfBirth().toString(),
                userEntity.getGender().name()
        );
    }

    @Override
    public UserEntity getUserEntityFromNewUser(NewUser newUser) {
        String encodedPassword = passwordEncoder.encode(newUser.password());
        FileResponse avatar;
        try {
            avatar = cromBucketWebClient.saveFile(newUser.avatar());
        } catch (IOException e) {
            throw new CromBucketClientException("Failed to save the file with message: " + e.getMessage());
        }
        return UserEntity.builder()
                .username(newUser.username())
                .password(encodedPassword)
                .email(newUser.email())
                .firstName(newUser.firstName())
                .lastName(newUser.lastName())
                .dateOfBirth(LocalDate.parse(newUser.dateOfBirth()))
                .gender(Gender.valueOf(newUser.gender().toUpperCase()))
                .avatar(avatar.getAccessUrl())
                .avatarId(avatar.getMediaId())
                .build();
    }
}

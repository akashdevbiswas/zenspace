package com.cromxt.zenspaceserver.service.impl;

import com.cromxt.zenspaceserver.dtos.request.NewUser;
import com.cromxt.zenspaceserver.dtos.request.UserCredential;
import com.cromxt.zenspaceserver.dtos.response.AuthTokens;
import com.cromxt.zenspaceserver.dtos.response.UserResponse;
import com.cromxt.zenspaceserver.entity.UserEntity;
import com.cromxt.zenspaceserver.exceptions.UserNotFoundException;
import com.cromxt.zenspaceserver.respository.UserRepository;
import com.cromxt.zenspaceserver.service.AuthService;
import com.cromxt.zenspaceserver.service.EntityMapper;
import com.cromxt.zenspaceserver.service.JWTService;
import com.cromxt.zenspaceserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements AuthService, UserService {

    private final UserRepository userRepository;
    private final EntityMapper entityMapper;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthTokens generateToken(UserCredential credential) {

        Optional<UserEntity> userEntityOptional = userRepository.findByUsernameOrEmail(credential.usernameOrEmail());

        UserEntity savedUser = userEntityOptional.orElseThrow(() -> new UserNotFoundException("Invalid username or email"));
        boolean matches = passwordEncoder.matches(credential.password(), savedUser.getPassword());

        if(!matches){
            throw new UserNotFoundException("Invalid username or Password");
        }
        String userId = savedUser.getId().toString();
        String accessToken = jwtService.generateAccessToken(userId, new HashMap<>());
        String refreshToken = jwtService.generateRefreshToken(userId);
        return new AuthTokens(accessToken, refreshToken);
    }

    @Override
    public AuthTokens generateAccessToken(String refreshToken) {
        String userId = jwtService.extractUsername(refreshToken);

        Optional<UserEntity> savedUserOptional = userRepository.findById(userId);

        UserEntity savedUser = savedUserOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String newAccessToken = jwtService.generateAccessToken(savedUser.getId().toString(), new HashMap<>());
        return new AuthTokens(newAccessToken, refreshToken);
    }

    @Override
    public UserResponse saveUser(NewUser newUser) {
        UserEntity userEntity = entityMapper.getUserEntityFromNewUser(newUser);
        UserEntity savedUser = userRepository.save(userEntity);
        return entityMapper.getUserResponseFromUserEntity(savedUser);
    }

}

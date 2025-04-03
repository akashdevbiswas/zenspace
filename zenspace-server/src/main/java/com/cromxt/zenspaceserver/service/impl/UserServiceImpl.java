package com.cromxt.zenspaceserver.service.impl;

import com.cromxt.zenspaceserver.dtos.request.NewUserRequest;
import com.cromxt.zenspaceserver.dtos.request.UserCredential;
import com.cromxt.zenspaceserver.dtos.response.AuthTokens;
import com.cromxt.zenspaceserver.entity.UserEntity;
import com.cromxt.zenspaceserver.entity.UserRole;
import com.cromxt.zenspaceserver.exceptions.UserNotFoundException;
import com.cromxt.zenspaceserver.respository.UserRepository;
import com.cromxt.zenspaceserver.service.AuthService;
import com.cromxt.zenspaceserver.service.EntityMapper;
import com.cromxt.zenspaceserver.service.JWTService;
import com.cromxt.zenspaceserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements AuthService, UserService{

    private final UserRepository userRepository;
    private final EntityMapper entityMapper;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

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
    public UserEntity getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

}

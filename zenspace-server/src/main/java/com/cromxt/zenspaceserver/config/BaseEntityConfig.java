package com.cromxt.zenspaceserver.config;


import com.cromxt.zenspaceserver.entity.PlatformPermissions;
import com.cromxt.zenspaceserver.entity.UserRole;
import com.cromxt.zenspaceserver.respository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class BaseEntityConfig {

    private final UserRoleRepository userRoleRepository;

    @Bean
    public UserRole adminRole(){
        UserRole userRole = UserRole.builder()
                .roleName("ROLE_ADMIN")
                .permissions(Set.of(PlatformPermissions.values()))
                .build();
        return userRoleRepository.save(userRole);
    }

    @Bean
    public CommandLineRunner rootRuleInitializer(
            @Qualifier("adminRole") UserRole adminRole
    ){
        return args -> userRoleRepository.save(adminRole);
    }
}

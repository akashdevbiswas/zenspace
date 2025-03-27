package com.cromxt.zenspaceserver.respository;

import com.cromxt.zenspaceserver.entity.Gender;
import com.cromxt.zenspaceserver.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private UserEntity userEntity;

    @BeforeEach
    void createAUser(){
        UserEntity userEntity = UserEntity.builder()
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .gender(Gender.MALE)
                .mediaId("mediaId")
                .avatarUrl("profileImage")
                .dateOfBirth(LocalDate.of(1999, 1, 1))
                .username("username")
                .password("password")
                .build();
        this.userEntity = userRepository.save(userEntity);
    }

    @AfterEach
    void deleteAUser(){
        userRepository.delete(userEntity);
    }

    @Test
    void findUserByUsernameOrEmail(){
        Optional<UserEntity> userByUserName = userRepository.findByUsernameOrEmail("username");
        Optional<UserEntity> userByEmail = userRepository.findByUsernameOrEmail("email");

        assertTrue(userByUserName.isPresent());
        assertTrue(userByEmail.isPresent());

        UserEntity usernameUser = userByUserName.get();
        UserEntity emailUser = userByEmail.get();

        assertEquals(usernameUser.getUsername(), emailUser.getUsername());
    }
}
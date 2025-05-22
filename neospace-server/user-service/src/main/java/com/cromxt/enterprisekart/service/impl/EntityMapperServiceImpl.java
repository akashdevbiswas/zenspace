package com.cromxt.enterprisekart.service.impl;

import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.stereotype.Service;

import com.cromxt.enterprisekart.dtos.requests.UserRequest;
import com.cromxt.enterprisekart.models.UserModel;
import com.cromxt.enterprisekart.service.EntityMapper;


@Service
public class EntityMapperServiceImpl implements EntityMapper {

  @Override
  public UserModel createUserModel(UserRequest userRequest) {
    return UserModel.builder()
    .username(userRequest.username())
    .password(userRequest.password())
    .role(userRequest.role())
    .firstName(userRequest.firstName())
    .lastName(userRequest.lastName())
    .build();
  }

}

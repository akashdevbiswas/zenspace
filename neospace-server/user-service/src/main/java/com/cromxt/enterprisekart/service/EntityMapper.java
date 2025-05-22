package com.cromxt.enterprisekart.service;

import com.cromxt.enterprisekart.dtos.requests.UserRequest;
import com.cromxt.enterprisekart.models.UserModel;

public interface EntityMapper {
  UserModel createUserModel(UserRequest userRequest);
}

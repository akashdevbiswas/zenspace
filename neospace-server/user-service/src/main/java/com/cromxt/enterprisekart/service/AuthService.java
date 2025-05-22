package com.cromxt.enterprisekart.service;

import com.cromxt.enterprisekart.dtos.requests.UserCreadentials;
import com.cromxt.enterprisekart.dtos.requests.UserRequest;
import com.cromxt.enterprisekart.dtos.responses.AuthToken;

public interface AuthService {
  void saveUser(UserRequest userRequest);

  AuthToken generateToken(UserCreadentials userCreadentials);
}

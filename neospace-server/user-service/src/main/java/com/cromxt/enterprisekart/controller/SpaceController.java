package com.cromxt.enterprisekart.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cromxt.enterprisekart.dtos.requests.SpaceRequest;
import com.cromxt.enterprisekart.service.SpaceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/spaces")
@RequiredArgsConstructor
public class SpaceController {

  private final SpaceService spaceService;

  @PostMapping
  public void createSpace(@RequestBody SpaceRequest spaceRequest){
    spaceService.saveSpace(spaceRequest);
  }
  
}

package com.cromxt.zenspaceserver.controller;

import com.cromxt.zenspaceserver.dtos.request.SpaceRequest;
import com.cromxt.zenspaceserver.dtos.response.SpaceResponse;
import com.cromxt.zenspaceserver.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/spaces")
@RequiredArgsConstructor
public class SpaceController {

    private final SpaceService spaceService;

    @PostMapping
    public SpaceResponse createSpace(@RequestBody SpaceRequest spaceRequest, @RequestAttribute String userId){
        return spaceService.createSpace(spaceRequest, userId);
    }

}

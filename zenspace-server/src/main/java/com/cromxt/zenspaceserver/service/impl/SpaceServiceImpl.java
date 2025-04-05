package com.cromxt.zenspaceserver.service.impl;


import com.cromxt.zenspaceserver.dtos.request.SpaceRequest;
import com.cromxt.zenspaceserver.dtos.response.SpacePageableResponse;
import com.cromxt.zenspaceserver.dtos.response.SpaceResponse;
import com.cromxt.zenspaceserver.entity.*;
import com.cromxt.zenspaceserver.respository.SpaceRepository;
import com.cromxt.zenspaceserver.respository.SpaceUserMembersRepository;
import com.cromxt.zenspaceserver.service.EntityMapper;
import com.cromxt.zenspaceserver.service.MediaObjectsService;
import com.cromxt.zenspaceserver.service.SpaceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaceServiceImpl implements SpaceService {

    private final SpaceRepository spaceRepository;
    private final EntityMapper entityMapper;
    private final MediaObjectsService mediaObjectsService;
    private final SpaceUserMembersRepository spaceUserMembersRepository;
    private final SpaceRules adminRule;
    private final SpaceRules memberRule;


    public SpaceServiceImpl(SpaceRepository spaceRepository,
                            EntityMapper entityMapper,
                            MediaObjectsService mediaObjectsService,
                            SpaceUserMembersRepository spaceUserMembersRepository,
                            @Qualifier("adminRule") SpaceRules adminRule,
                            @Qualifier("memberRule") SpaceRules memberRule) {
        this.spaceRepository = spaceRepository;
        this.entityMapper = entityMapper;
        this.mediaObjectsService = mediaObjectsService;
        this.spaceUserMembersRepository = spaceUserMembersRepository;
        this.adminRule = adminRule;
        this.memberRule = memberRule;
    }

    @Override
    @Transactional
    public SpaceResponse createSpace(SpaceRequest newSpace, String userId) {
        MediaObjects savedMedia = mediaObjectsService.saveMediaObject(newSpace.spaceProfileImage());
        Space space = entityMapper.createSpaceFromSpaceRequest(newSpace, savedMedia);

        Space savedSpace = spaceRepository.save(space);

        SpaceUserMembers spaceUserMembers = entityMapper.createSpaceUserMembers(savedSpace, UserEntity.builder()
                .id(userId)
                .build(), adminRule);

        SpaceUserMembers newSpaceUserMembers = spaceUserMembersRepository.save(spaceUserMembers);

        return entityMapper.getSpaceResponseFromSpaceEntity(newSpaceUserMembers.getId().getSpace(), newSpaceUserMembers.getId().getRule());
    }

    @Override
    public SpacePageableResponse findSpacesBySpaceName(String spaceName, Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Space> spacePage = spaceRepository.findByNameLike(spaceName, pageable);

        List<SpaceResponse> spaceList = spacePage.get().map(space -> SpaceResponse.builder()
                .id(space.getId())
                .name(space.getName())
                .spaceProfileImage(space.getMediaObjects().getUrl())
                .build()).toList();
        return entityMapper.createSpacePageableResponse(
                spaceList,
                spacePage.getNumber(),
                spacePage.getSize(),
                spacePage.isLast()
        );
    }

    @Override
    public SpacePageableResponse getAllSpacesByUserId(String userId, Integer pageNumber, Integer pageSize) {

        Example<SpaceUserMembers> spaceUserMembersExample = Example.of(SpaceUserMembers.builder()
                .id(SpaceUserMembersKey.builder()
                        .user(UserEntity.builder()
                                .id(userId)
                                .build())
                        .build()).build());

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<SpaceUserMembers> spaceUserMembersPage = spaceUserMembersRepository.findAll(spaceUserMembersExample, pageable);

        List<SpaceResponse> spaceList = spaceUserMembersPage.getContent().stream().map(spaceUsers -> {
            Space space = spaceUsers.getId().getSpace();
            return entityMapper.getSpaceResponseFromSpaceEntity(space, spaceUsers.getId().getRule());
        }).toList();

        return entityMapper.createSpacePageableResponse(
                spaceList,
                spaceUserMembersPage.getNumber(),
                spaceUserMembersPage.getSize(),
                spaceUserMembersPage.isLast()
        );
    }

    @Override
    public void deleteSpace(String spaceId) {

    }

    @Override
    public SpaceResponse updateSpace(String spaceId, SpaceRequest spaceRequest) {
        return null;
    }
}

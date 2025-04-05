package com.cromxt.zenspaceserver.service.impl;

import com.cromxt.zenspaceserver.entity.MediaObjects;
import com.cromxt.zenspaceserver.service.MediaObjectsService;
import com.cromxt.zenspaceserver.service.UtilService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;


@Service
public class UtilServiceImpl implements UtilService, MediaObjectsService {
    private static final String[] DEFAULT_AVATARS = {
            "default.png",
            "hello-kitty.png",
            "jerry.png",
            "patrick.png",
            "shinchan.png",
            "squid-ward.png",
            "shiro.png"
    };

    @Override
    public MediaObjects saveMediaObject(MultipartFile multipartFile) {
        return null;
    }

    @Override
    public MediaObjects deleteMediaObject(String mediaId) {
        return null;
    }

    @Override
    public MediaObjects updateMediaObjects(String mediaId, MultipartFile multipartFile) {
        return null;
    }

    @Override
    public MediaObjects getMediaObjects(String mediaId) {
        return null;
    }

    @Override
    public List<String> getAllAvailableAvatars() {
        return Arrays.stream(DEFAULT_AVATARS).map(this::generateAvatarUrl).toList();
    }

    @Override
    public String getRandomAvatar() {
        return null;
    }

    @Override
    public String getAvatarUrlByAvatarId(String avatarId) {
        return "";
    }


    private String generateAvatarUrl(String avatar) {
        String hostname= "http://localhost:8901";
        return String.format("%s/images/%s", hostname,avatar);
    }


}

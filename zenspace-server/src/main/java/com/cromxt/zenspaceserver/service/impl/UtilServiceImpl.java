package com.cromxt.zenspaceserver.service.impl;

import com.cromxt.zenspaceserver.entity.MediaObjects;
import com.cromxt.zenspaceserver.service.MediaObjectService;
import com.cromxt.zenspaceserver.service.UtilService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;


@Service
public class UtilServiceImpl implements UtilService, MediaObjectService {
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
    public MediaObjects deleteMedia(String mediaId) {
        return null;
    }

    @Override
    public MediaObjects updateMedia(String mediaId, MultipartFile multipartFile) {
        return null;
    }

    @Override
    public MediaObjects getMedia(String mediaId) {
        return null;
    }

    @Override
    public List<String> getAllAvailableAvatars() {
        return Arrays.stream(DEFAULT_AVATARS).map(this::generateAvatarUrl).toList();
    }

    @Override
    public String getDefaultAvatarUrl() {
        return generateAvatarUrl(DEFAULT_AVATARS[0]);
    }

    @Override
    public String getAvatarUrlByIndex(int index) {
        if(DEFAULT_AVATARS.length <=  index) {
            return getDefaultAvatarUrl();
        }
        return generateAvatarUrl(DEFAULT_AVATARS[index]);
    }

    private String generateAvatarUrl(String avatar) {
        String hostname= "http://localhost:8901";
        return String.format("%s/images/%s", hostname,avatar);
    }


}

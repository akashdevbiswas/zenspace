package com.cromxt.zenspaceserver.service.impl;

import com.cromxt.zenspaceserver.service.UtilService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class UtilServiceImpl implements UtilService {
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

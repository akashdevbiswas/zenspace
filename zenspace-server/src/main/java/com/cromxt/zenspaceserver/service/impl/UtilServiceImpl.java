package com.cromxt.zenspaceserver.service.impl;

import com.cromxt.zenspaceserver.service.UtilService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class UtilServiceImpl implements UtilService {
    private static final String[] DEFAULT_AVATARS = {
            "gary.png",
            "hello-kitty.png",
            "jerry.png",
            "patrick.png",
            "shinchan.png",
            "squid-ward.png",
            "shiro.png"
    };
    @Override
    public List<String> getAllAvailableAvatars() {
        String hostname= "http://localhost:8901";
        return Arrays.stream(DEFAULT_AVATARS).map(avatar -> String.format("%s/images/%s", hostname,avatar)).toList();
    }

}

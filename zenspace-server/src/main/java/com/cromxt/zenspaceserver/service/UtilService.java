package com.cromxt.zenspaceserver.service;


import java.util.List;

public interface UtilService {

    List<String> getAllAvailableAvatars();
    String getDefaultAvatarUrl();
    String getAvatarUrlByIndex(int index);
}

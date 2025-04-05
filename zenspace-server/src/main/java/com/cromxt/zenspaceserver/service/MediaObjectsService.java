package com.cromxt.zenspaceserver.service;

import com.cromxt.zenspaceserver.entity.MediaObjects;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaObjectsService {

    MediaObjects saveMediaObject(MultipartFile multipartFile);

    MediaObjects deleteMediaObject(String mediaId);

    MediaObjects updateMediaObjects(String mediaId, MultipartFile multipartFile);

    MediaObjects getMediaObjects(String mediaId);

    List<String> getAllAvailableAvatars();

    String getRandomAvatar();

    String getAvatarUrlByAvatarId(int index);

}

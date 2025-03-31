package com.cromxt.zenspaceserver.service;

import com.cromxt.zenspaceserver.entity.MediaObjects;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaObjectService {

    MediaObjects saveMediaObject(MultipartFile multipartFile);

    MediaObjects deleteMedia(String mediaId);

    MediaObjects updateMedia(String mediaId, MultipartFile multipartFile);

    MediaObjects getMedia(String mediaId);

    List<String> getAllAvailableAvatars();

    String getDefaultAvatarUrl();

    String getAvatarUrlByIndex(int index);

}

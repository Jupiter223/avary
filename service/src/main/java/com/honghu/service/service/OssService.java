package com.honghu.service.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String uploadFilePic(MultipartFile file);
}

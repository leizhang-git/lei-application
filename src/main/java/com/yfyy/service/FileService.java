package com.yfyy.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: zhang lei
 * @Date: 2022/4/10 13:00
 */
public interface FileService {

    String uploadFile(MultipartFile file, String dir);
}

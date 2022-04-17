package com.yfyy.service.impl;

import com.yfyy.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: zhang lei
 * @Date: 2022/4/10 13:00
 */
@Service
public class FileServiceImpl implements FileService {

    private final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String uploadFile(MultipartFile file, String dir) {
        File targetFile = new File("/Users/leiz/Documents/application/test");
        try {
            if(!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
        } catch (IOException e) {
            log.error("file upload error.", e);
            return "failed";
        }
        return "success";
    }
}

package com.yfyy.rest;

import com.yfyy.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: zhang lei
 * @Date: 2022/4/10 12:53
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private final Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     * @param file
     * @param dir
     * @return
     */
    @PostMapping("/upload/file")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file, String dir) {
        if(file.isEmpty()) {
            log.error("file is empty..");
            throw new RuntimeException("文件不存在！上传失败");
        }
        File targetFile = new File("/Users/leiz/Documents/application/test");
        try {
            if(!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
        } catch (IOException e) {
            log.error("file upload error.", e);
        }
        return "";
    }
}

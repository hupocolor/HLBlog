package com.hl.controller;

import com.hl.domain.ResponseResult;
import com.hl.domain.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author : hupo, 创建于:2023/3/23
 */
@RestController
@RequestMapping
public class FileController {
    @Autowired
    private FileService fileService;
    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img){
        return fileService.upload(img);
    }
}

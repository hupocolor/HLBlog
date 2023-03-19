package com.hl.domain.service;

import com.hl.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author : hupo, 创建于:2023/3/18
 */
public interface FileService {
    ResponseResult upload(MultipartFile img);
}

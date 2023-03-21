package com.hl.controller;

import com.hl.domain.ResponseResult;
import com.hl.domain.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : hupo, 创建于:2023/3/21
 */
@RestController
@RequestMapping("content/tag")
public class TagController {
    @Autowired
    TagService tagService;
    @GetMapping("/list")
    public ResponseResult list(){
        return ResponseResult.okResult(tagService.list());
    }
}

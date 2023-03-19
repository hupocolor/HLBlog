package com.hl.controller;

import com.hl.annotation.SystemLog;
import com.hl.domain.ResponseResult;
import com.hl.domain.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : hupo, 创建于:2023/3/15
 */
@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    LinkService linkService;

    @GetMapping("/getAllLink")
    @SystemLog(detailInfo = "获取友链列表")
    public ResponseResult getAllLink(){
        return linkService.getAllLinkResult();
    }
}

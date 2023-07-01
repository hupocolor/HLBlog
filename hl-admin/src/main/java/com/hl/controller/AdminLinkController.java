package com.hl.controller;

import com.hl.domain.ResponseResult;
import com.hl.domain.entity.Link;
import com.hl.domain.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : hupo, 创建于:2023/3/30
 */
@RestController
@RequestMapping("/content/link")
public class AdminLinkController {
    @Autowired
    LinkService linkService;
    @GetMapping("/list")
    public ResponseResult listByKeywords(Integer pageNum,Integer pageSize,String name,String status){
        return linkService.listByKeywords(pageNum,pageSize,name,status);
    }
    @PostMapping()
    public ResponseResult addLink(@RequestBody Link link){
        return ResponseResult.okResult(linkService.save(link));
    }
    @GetMapping("/{id}")
    public ResponseResult getById(@PathVariable Long id){
        return ResponseResult.okResult(linkService.getById(id));
    }

    @PutMapping()
    public ResponseResult update(@RequestBody Link link){
        return ResponseResult.okResult(linkService.updateById(link));
    }
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        return ResponseResult.okResult(linkService.removeById(id));
    }
}

package com.hl.controller;

import com.hl.annotation.SystemLog;
import com.hl.domain.ResponseResult;
import com.hl.domain.service.BlogService;
import com.hl.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Author : hupo, 创建于:2023/3/12
 */
@RestController
@RequestMapping("/article")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @GetMapping("/hotArticleList")
    @SystemLog(detailInfo = "热门文章列表")
    public ResponseResult hotBlogList(){
        //查询热门文章,封装成ResponseResult的json返回
        ResponseResult result = blogService.getHotList();
        return result;
    }
    @GetMapping("/articleList")
    @SystemLog(detailInfo = "文章列表")
    public ResponseResult articleList(Integer pageNum, Integer pageSize,Integer categoryId){
        return blogService.blogList(pageNum,pageSize,categoryId);
    }

    @GetMapping("/{id}")
    @SystemLog(detailInfo = "获取文章详情")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return blogService.getDetail(id);
    }

    @PutMapping("/updateViewCount/{id}")
    @SystemLog(detailInfo = "获取浏览量")
    public ResponseResult updateViews(@PathVariable("id") Long id){
        return blogService.updateViews(id);
    }

}

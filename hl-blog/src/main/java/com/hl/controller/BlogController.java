package com.hl.controller;

import com.hl.annotation.SystemLog;
import com.hl.domain.ResponseResult;
import com.hl.domain.service.BlogService;
import com.hl.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    @GetMapping("/test")
//    public String getTest() throws Exception {
//        Claims claims = JwtUtil.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiNzdiN2VhZWQ5MmE0MWJjYjMxZDIyZjdlMGUxOGVlZCIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTY3ODk2NzUwNSwiZXhwIjoxNjc5MDUzOTA1fQ.UHLVlUkpJ_-16IqTH29m6xp85PyUrrX5_rV7G8IVVGE");
//        return claims.getSubject();
//    }
}

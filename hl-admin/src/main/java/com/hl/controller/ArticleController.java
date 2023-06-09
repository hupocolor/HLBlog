package com.hl.controller;

import com.hl.domain.ResponseResult;
import com.hl.domain.dto.AddArticleDto;
import com.hl.domain.service.BlogService;
import com.hl.utils.ArticleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private BlogService blogService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article){
        return blogService.add(article);
    }

    @GetMapping("/list")
    public ResponseResult getList(Integer pageNum,Integer pageSize,String title,String summary){
        return blogService.getListByKeyWords(pageNum,pageSize,title,summary);
    }

    @GetMapping("{id}")
    public ResponseResult getDetail(@PathVariable Long id){
        return blogService.getAdminDetail(id);
    }

    @PutMapping()
    public ResponseResult updateBlog(@RequestBody AddArticleDto dto){
        blogService.updateById(ArticleUtils.ArticleDtotoBlog(dto));
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        blogService.removeById(id);
        return ResponseResult.okResult();
    }
}
package com.hl.controller;

import com.hl.annotation.SystemLog;
import com.hl.domain.ResponseResult;
import com.hl.domain.entity.Comment;
import com.hl.domain.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : hupo, 创建于:2023/3/18
 */
@RestController
@RequestMapping("/comment")
@Tag(name = "评论",description = "评论相关接口")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/commentList")
    @SystemLog(detailInfo = "获取文章评论列表")
    @Operation(summary = "文章评论列表",description = "获取一页文章评论")
    @Parameters({@Parameter(name = "articleId", description = "文章id"), @Parameter(name = "pageNum", description = "当前页"),
    @Parameter(name = "pageSize",description = "单页评论数")})
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.rootCommentList("0",articleId,pageNum,pageSize);
    }
    @GetMapping("/linkCommentList")
    @SystemLog(detailInfo = "获取友链文章列表")
    @Operation(summary = "友链评论列表",description = "获取一页友链评论")
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.rootCommentList("1",null,pageNum,pageSize);
    }
    @PostMapping()
    @SystemLog(detailInfo = "新增评论")
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
}

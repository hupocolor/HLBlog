package com.hl.controller;

import com.hl.annotation.SystemLog;
import com.hl.domain.ResponseResult;
import com.hl.domain.entity.Comment;
import com.hl.domain.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : hupo, 创建于:2023/3/18
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/commentList")
    @SystemLog(detailInfo = "获取文章评论列表")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.rootCommentList("0",articleId,pageNum,pageSize);
    }
    @GetMapping("/linkCommentList")
    @SystemLog(detailInfo = "获取友链文章列表")
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.rootCommentList("1",null,pageNum,pageSize);
    }
    @PostMapping()
    @SystemLog(detailInfo = "新增评论")
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
}

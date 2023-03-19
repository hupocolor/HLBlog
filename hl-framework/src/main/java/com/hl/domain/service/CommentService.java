package com.hl.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hl.domain.ResponseResult;
import com.hl.domain.entity.Comment;

/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-03-16 22:09:06
 */
public interface CommentService extends IService<Comment> {

    ResponseResult rootCommentList(String type,Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}


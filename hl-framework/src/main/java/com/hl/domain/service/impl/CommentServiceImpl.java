package com.hl.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hl.domain.ResponseResult;
import com.hl.domain.entity.Blog;
import com.hl.domain.mapper.CommentMapper;
import com.hl.domain.entity.Comment;
import com.hl.domain.mapper.UserMapper;
import com.hl.domain.service.CommentService;
import com.hl.domain.vo.CommentVo;
import com.hl.domain.vo.PageVo;
import com.hl.enums.AppHttpCodeEnum;
import com.hl.exception.SysException;
import com.hl.utils.BeanCopyUtils;
import com.hl.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-03-16 22:09:06
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    UserMapper userMapper;
    @Override
    public ResponseResult rootCommentList(String type,Long articleId, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Comment::getType,type); //文章评论还是友联评论
        if (articleId!=null)
            lambdaQueryWrapper.eq(Comment::getArticleId,articleId);
        lambdaQueryWrapper.orderByDesc(Comment::getUpdateTime);
        //获取到这个文章下的所有评论
        List<Comment> comments = list(lambdaQueryWrapper);
        lambdaQueryWrapper.eq(Comment::getRootId,-1); //-1代表没有根评论
        Page<Comment> page = new Page<>(pageNum,pageSize);
        List<Comment> commentList = page(page, lambdaQueryWrapper).getRecords();
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(commentList, CommentVo.class);
        Map<Long,CommentVo> hashMap = new HashMap<>();
        for (CommentVo commentVo : commentVos) {
            //用一个哈希表辅助存储根评论
            commentVo.creatChildren();
            hashMap.put(commentVo.getId(),commentVo);
            setNameAndId(commentVo);
        }
        for (Comment comment : comments) {
            if (hashMap.containsKey(comment.getRootId())){
                CommentVo commentVo = BeanCopyUtils.copyBean(comment, CommentVo.class);
                hashMap.get(comment.getRootId()).getChildren().add(commentVo);
                setNameAndId(commentVo);
            }
        }
        return ResponseResult.okResult(new PageVo(commentVos,page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        if (!StringUtils.hasText(comment.getContent())){
            throw new SysException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    private void setNameAndId(CommentVo commentVo){
        commentVo.setAvatarImg(userMapper.selectById(commentVo.getCreateBy()).getAvatar());
        commentVo.setUsername(userMapper.selectById(commentVo.getCreateBy()).getNickName());
        if (commentVo.getToCommentId()!=-1 && !Objects.isNull(userMapper.selectById(commentVo.getToCommentUserId())))
            commentVo.setToCommentUserName(userMapper.selectById(commentVo.getToCommentUserId()).getNickName());
    }
}


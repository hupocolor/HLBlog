package com.hl.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author : hupo, 创建于:2023/3/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    private Long id;
    //文章id
    private Long articleId;
    //根评论id
    private Long rootId;
    //评论内容
    private String content;
    //所回复的目标评论的userid
    private Long toCommentUserId;
    private String toCommentUserName;
    //回复目标评论id
    private Long toCommentId;
    private Long createBy;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String username;
    private List<CommentVo> children;
    private String avatarImg;

    public void creatChildren(){
        children = new ArrayList<>();
    }
}

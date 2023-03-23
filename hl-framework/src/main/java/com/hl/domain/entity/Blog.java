package com.hl.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Blog)表实体类
 *
 * @author makejava
 * @since 2023-03-12 15:39:12
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("hl_blog")
public class Blog {
    //博客表主键id
    @TableId
    private Long blogId;
    //博客标题
    private String blogTitle;
    //博客自定义路径url
    private String blogSubUrl;
    //博客封面图
    private String blogCoverImage;
    //博客内容
    private String content;
    //博客分类id
    private Integer blogCategoryId;
    //博客分类名称
    @TableField(exist = false)
    private String categoryName;
    //博客标签
    private String blogTags;
    //0-草稿 1-发布
    private Integer blogStatus;
    //阅读量
    private Long blogViews;
    //是否置顶,1是 0否
    private Integer isTop;
    //0-允许评论 1-不允许评论
    private Integer enableComment;
    //是否删除 0=否 1=是
    private Integer delFlag;
    //添加时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //文章摘要
    private String blogSummary;


}


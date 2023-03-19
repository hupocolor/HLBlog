package com.hl.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author : hupo, 创建于:2023/3/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDetailVo {
    private Long blogId;
    //博客标题
    private String blogTitle;
    //博客自定义路径url
    private String blogSubUrl;
    //博客封面图
    private String blogCoverImage;
    //博客内容
    private String content;
    //博客分类名
    private String categoryName;
    //分类id
    private Integer blogCategoryId;
    //博客标签
    private String blogTags;
    //阅读量
    private Long blogViews;
    //添加时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //文章摘要
    private String blogSummary;
}

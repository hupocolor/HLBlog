package com.hl.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (BlogTag)表实体类
 *
 * @author makejava
 * @since 2023-03-23 16:49:01
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("hl_blog_tag")
public class BlogTag {
    //文章id
    private Long blogId;
    //标签id
    private Long tagId;

}


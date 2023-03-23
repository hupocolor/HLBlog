package com.hl.utils;

import com.hl.domain.dto.AddArticleDto;
import com.hl.domain.entity.Blog;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : hupo, 创建于:2023/3/23
 */
public class ArticleUtils {

    public static Blog ArticleDtotoBlog(AddArticleDto dto){
        Blog blog = new Blog();
        blog.setBlogId(dto.getId());
        blog.setBlogTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        blog.setBlogSummary(dto.getSummary());
        blog.setBlogCategoryId(Math.toIntExact(dto.getCategoryId()));
        blog.setBlogCoverImage(dto.getThumbnail());
        blog.setIsTop(Integer.valueOf(dto.getIsTop()));
        blog.setBlogStatus(Integer.valueOf(dto.getStatus()));
        blog.setEnableComment(Integer.valueOf(dto.getIsComment()));
        blog.setBlogViews(dto.getViewCount());
        return blog;
    }
    public static AddArticleDto blogToDto(Blog blog){
        AddArticleDto dto = new AddArticleDto();
        dto.setId(blog.getBlogId());
        dto.setTitle(blog.getBlogTitle());
        dto.setContent(blog.getContent());
        dto.setSummary(blog.getBlogSummary());
        dto.setCategoryId(Long.valueOf(blog.getBlogCategoryId()));
        dto.setThumbnail(blog.getBlogCoverImage());
        dto.setIsTop(String.valueOf(blog.getIsTop()));
        dto.setStatus(String.valueOf(blog.getBlogStatus()));
        dto.setIsComment(String.valueOf(blog.getEnableComment()));
        dto.setViewCount(blog.getBlogViews());
        dto.setCreateTime(blog.getCreateTime());
        dto.setUpdateTime(blog.getUpdateTime());
        return dto;
    }

    public static List<AddArticleDto> ListBlogToDto(List<Blog> blogs) {
        List<AddArticleDto> res = new ArrayList<>();
        for (Blog blog : blogs) {
            res.add(blogToDto(blog));
        }
        return res;
    }
}

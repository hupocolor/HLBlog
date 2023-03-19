package com.hl.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hl.domain.mapper.CategoryMapper;
import com.hl.domain.ResponseResult;
import com.hl.domain.entity.Blog;
import com.hl.domain.entity.Category;
import com.hl.domain.service.BlogService;
import com.hl.domain.service.CategoryService;
import com.hl.domain.vo.CategoryVo;
import com.hl.enums.ArticleStatus;
import com.hl.enums.CategoryStatus;
import com.hl.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-03-13 21:41:53
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private BlogService blogService;

    @Override
    public ResponseResult getCategoryList() {
        LambdaQueryWrapper<Blog> blogLambdaQueryWrapper = new LambdaQueryWrapper<>();
        blogLambdaQueryWrapper.eq(Blog::getBlogStatus, ArticleStatus.NORMAL.getStatus());
        //查询文章表状态为已发布
        List<Blog> blogs = blogService.list(blogLambdaQueryWrapper);
        //查询文章表id,去重
        Set<Integer> categoryIds = blogs.stream()
                .map(blog -> blog.getBlogCategoryId())
                .collect(Collectors.toSet());
        //根据id,查询分类表
        System.out.println(categoryIds);
        List<Category> categories = listByIds(categoryIds);
        System.out.println(categories);
        //正常状态

              categories = categories.stream()
                .filter(category -> category.getStatus().equals(CategoryStatus.NORMAL.getStatus()))
                .collect(Collectors.toList());
        //封装成vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }
}


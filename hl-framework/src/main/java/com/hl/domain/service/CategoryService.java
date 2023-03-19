package com.hl.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hl.domain.ResponseResult;
import com.hl.domain.entity.Category;

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-03-13 21:41:53
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}


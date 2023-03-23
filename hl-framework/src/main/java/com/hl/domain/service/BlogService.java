package com.hl.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hl.domain.ResponseResult;
import com.hl.domain.dto.AddArticleDto;
import com.hl.domain.entity.Blog;

/**
 * (Blog)表服务接口
 *
 * @author makejava
 * @since 2023-03-12 15:48:01
 */
public interface BlogService extends IService<Blog> {
    ResponseResult getHotList();

    ResponseResult blogList(Integer pageNum, Integer pageSize, Integer categoryId);

    ResponseResult getDetail(Long id);

    ResponseResult updateViews(Long id);

    ResponseResult add(AddArticleDto article);

    ResponseResult getListByKeyWords(Integer pageNum, Integer pageSize, String title, String summary);
}


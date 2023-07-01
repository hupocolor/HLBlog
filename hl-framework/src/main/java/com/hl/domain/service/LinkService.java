package com.hl.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hl.domain.ResponseResult;
import com.hl.domain.entity.Link;

/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-03-15 13:47:46
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLinkResult();

    ResponseResult listByKeywords(Integer pageNum, Integer pageSize, String name, String status);
}


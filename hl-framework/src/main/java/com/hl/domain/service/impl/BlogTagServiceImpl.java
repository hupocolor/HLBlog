package com.hl.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hl.domain.mapper.BlogTagMapper;
import com.hl.domain.entity.BlogTag;
import com.hl.domain.service.BlogTagService;
import org.springframework.stereotype.Service;

/**
 * (BlogTag)表服务实现类
 *
 * @author makejava
 * @since 2023-03-23 16:49:01
 */
@Service("blogTagService")
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

}


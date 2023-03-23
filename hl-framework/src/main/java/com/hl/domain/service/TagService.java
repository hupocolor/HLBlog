package com.hl.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hl.domain.ResponseResult;
import com.hl.domain.dto.TagListDto;
import com.hl.domain.entity.Tag;
import com.hl.domain.vo.PageVo;

/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-03-21 17:02:02
 */
public interface TagService extends IService<Tag> {
    //TODO 标签的管理

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);
}


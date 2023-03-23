package com.hl.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hl.domain.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 标签(Tag)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-21 17:02:02
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    List<Long> selectByBlogId(@Param("id") Long id);
}


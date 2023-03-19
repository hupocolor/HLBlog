package com.hl.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hl.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类表(Category)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-13 21:41:53
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}


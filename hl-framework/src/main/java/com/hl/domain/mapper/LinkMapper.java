package com.hl.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hl.domain.entity.Link;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 友链(Link)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-15 13:47:46
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

    List<Link> selectByStatus();
}


package com.hl.domain.mapper;
import java.util.List;
import java.util.Map;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hl.domain.entity.Blog;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author : hupo, 创建于:2023/3/12
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

}

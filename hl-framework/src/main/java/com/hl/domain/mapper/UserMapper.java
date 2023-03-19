package com.hl.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hl.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-15 16:02:34
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectByUserName(@Param("userName") String userName);
}


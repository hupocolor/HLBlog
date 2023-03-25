package com.hl.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hl.domain.entity.Role;
import com.hl.domain.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Role)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-21 19:25:20
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(@Param("id") Long id);

    List<RoleMenu> getRoleMenuList(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    void insertRoleMenu(@Param("roleId") Long roleId, @Param("menuId") Long menuId);
}


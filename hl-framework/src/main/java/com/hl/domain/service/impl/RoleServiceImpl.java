package com.hl.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hl.domain.ResponseResult;
import com.hl.domain.mapper.RoleMapper;
import com.hl.domain.entity.Role;
import com.hl.domain.service.RoleService;
import com.hl.domain.vo.PageVo;
import com.hl.domain.vo.RoleVo;
import com.hl.utils.BeanCopyUtils;
import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * (Role)表服务实现类
 *
 * @author makejava
 * @since 2023-03-21 19:25:20
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public ResponseResult getList(Integer pageNum, Integer pageSize, String roleName, Integer status) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper();
        if (Strings.hasText(roleName))
            wrapper.like(Role::getRoleName,roleName);
        if (!Objects.isNull(status))
            wrapper.eq(Role::getStatus,status);
        Page<Role> page = new Page<>(pageNum, pageSize);
        List<Role> records = page(page, wrapper).getRecords();
        return ResponseResult.okResult(new PageVo(records,page(page, wrapper).getTotal()));
    }

    @Override
    public ResponseResult updateStatus(Map<String, Object> idAndStatus) {
        Role role = getById((Serializable) idAndStatus.get("RoleId"));
        role.setStatus(Integer.valueOf((String)idAndStatus.get("status")));
        updateById(role);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateRoleVo(RoleVo roleVo) {
        Role role = BeanCopyUtils.copyBean(roleVo, Role.class);
        saveOrUpdate(role);
        for (Long menuId : roleVo.getMenuIds()) {
            if (roleMapper.getRoleMenuList(role.getId(),menuId) != null){
                roleMapper.insertRoleMenu(role.getId(),menuId);
            }
        }
        return ResponseResult.okResult();
    }
}


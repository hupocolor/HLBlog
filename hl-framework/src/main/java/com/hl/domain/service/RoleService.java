package com.hl.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hl.domain.ResponseResult;
import com.hl.domain.entity.Role;
import com.hl.domain.vo.RoleVo;

import java.util.Map;

/**
 * (Role)表服务接口
 *
 * @author makejava
 * @since 2023-03-21 19:25:20
 */
public interface RoleService extends IService<Role> {

    ResponseResult getList(Integer pageNum, Integer pageSize, String roleName, Integer status);

    ResponseResult updateStatus(Map<String, Object> idAndStatus);

    ResponseResult updateRoleVo(RoleVo roleVo);
}


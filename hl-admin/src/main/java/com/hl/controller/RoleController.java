package com.hl.controller;

import com.hl.domain.ResponseResult;
import com.hl.domain.service.RoleService;
import com.hl.domain.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author : hupo, 创建于:2023/3/25
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @GetMapping("/list")
    public ResponseResult getRoleList(Integer pageNum,Integer pageSize,String roleName,Integer status){
        return roleService.getList(pageNum,pageSize,roleName,status);
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody Map<String,Object> idAndStatus){
        return roleService.updateStatus(idAndStatus);
    }

    @PutMapping
    public ResponseResult update(@RequestBody RoleVo roleVo){
        return roleService.updateRoleVo(roleVo);
    }
    @DeleteMapping("{id}")
    public ResponseResult delete(@PathVariable Long id){
        return ResponseResult.okResult(roleService.removeById(id));
    }
}

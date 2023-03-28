package com.hl.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hl.domain.ResponseResult;
import com.hl.domain.entity.Menu;
import com.hl.domain.service.MenuService;
import com.hl.domain.service.RoleService;
import com.hl.enums.AppHttpCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : hupo, 创建于:2023/3/23
 */
@RestController
@RequestMapping("system/menu")
public class MenuController {
    @Autowired
    MenuService menuService;
    @GetMapping("/list")
    public ResponseResult getMenuList(String status,String menuName){
        return menuService.getMenuList(status,menuName);
    }

    @PostMapping()
    public ResponseResult addMenu(@RequestBody Menu menu){
        menuService.save(menu);
        return ResponseResult.okResult();
    }

    @GetMapping("{id}")
    public ResponseResult getMenuDetail(@PathVariable Long id){
        Menu byId = menuService.getById(id);
        return ResponseResult.okResult(byId);
    }

    @PutMapping()
    public ResponseResult updateMenu(@RequestBody Menu menu){
        if(menu.getParentId() == menu.getId())
            return ResponseResult.errorResult(500,"上级菜单不能选择自己");
        menuService.updateById(menu);
        return ResponseResult.okResult();
    }

    @DeleteMapping("{menuId}")
    public ResponseResult deleteById(@PathVariable Long menuId){
        if (!menuService.list(new LambdaQueryWrapper<Menu>().eq(Menu::getParentId,menuId)).isEmpty()){
            return ResponseResult.errorResult(500,"存在子菜单不能删除");
        }
        menuService.removeById(menuId);
        return ResponseResult.okResult();
    }

    @GetMapping("/roleMenuTreeselect/{id}")
    public ResponseResult roleMenuTreeSelectById(@PathVariable Long id){
        return menuService.getTree(id);
    }

    @GetMapping("/treeselect")
    public ResponseResult treeSelect(){
        return menuService.getTreeAll();
    }
}

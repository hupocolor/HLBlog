package com.hl.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hl.domain.ResponseResult;
import com.hl.domain.entity.Menu;

/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2023-03-21 18:22:56
 */
public interface MenuService extends IService<Menu> {

    ResponseResult getRouters();

    ResponseResult getMenuList(String status, String menuName);

    ResponseResult getTree(Long id);
}


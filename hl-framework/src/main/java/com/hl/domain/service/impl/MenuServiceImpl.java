package com.hl.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hl.domain.ResponseResult;
import com.hl.domain.mapper.MenuMapper;
import com.hl.domain.entity.Menu;
import com.hl.domain.service.MenuService;
import com.hl.domain.vo.MenuVo;
import com.hl.utils.BeanCopyUtils;
import com.hl.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2023-03-21 18:22:56
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    MenuMapper menuMapper;
    @Override
    public ResponseResult getRouters() {
        HashMap<String, List> res = new HashMap();
        Long userId = SecurityUtils.getUserId();
        List<MenuVo> rootVos = getMenuList(0L,userId);
        for (MenuVo rootVo : rootVos) {
            rootVo.getChildren().addAll(getMenuList(rootVo.getId(),userId));
            for (MenuVo child : rootVo.getChildren()) {
                child.getChildren().addAll(getMenuList(child.getId(),userId));
            }
//            System.out.println("父节点"+rootVo.getMenuName()+"的子节点为:"+rootVo.getChildren());
        }
        res.put("menus",rootVos);
        return ResponseResult.okResult(res);
    }
    private List<MenuVo> getMenuList(Long MenuId,Long userId){
        if (userId == 1L) {
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Menu::getParentId, MenuId);
            wrapper.orderByDesc(Menu::getMenuName);
            wrapper.eq(Menu::getMenuType,"C");
            wrapper.or().eq(Menu::getMenuType,"M").eq(Menu::getParentId, MenuId);
            return BeanCopyUtils.copyBeanList(list(wrapper), MenuVo.class);
        }
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getParentId, MenuId);
        wrapper.orderByDesc(Menu::getMenuName);
        wrapper.eq(Menu::getMenuType,"C").or().
                eq(Menu::getMenuType,"M");
        List<Menu> list = list(wrapper);
        HashSet<Long> menuIdByUserId = menuMapper.selectMenuIdByUserId(userId);
        //列表中存在菜单的id在哈希集合中,则移出
        list.removeIf(menu -> menuIdByUserId.contains(menu.getId()));
        return BeanCopyUtils.copyBeanList(list(wrapper), MenuVo.class);
    }
}


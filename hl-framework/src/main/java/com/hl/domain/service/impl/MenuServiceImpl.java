package com.hl.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hl.domain.ResponseResult;
import com.hl.domain.mapper.MenuMapper;
import com.hl.domain.entity.Menu;
import com.hl.domain.mapper.RoleMapper;
import com.hl.domain.service.MenuService;
import com.hl.domain.vo.MenuTreeNode;
import com.hl.domain.vo.MenuVo;
import com.hl.domain.vo.RoleAndMenuVo;
import com.hl.utils.BeanCopyUtils;
import com.hl.utils.SecurityUtils;
import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public ResponseResult getMenuList(String status, String menuName) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Menu::getOrderNum);
        if (Strings.hasText(status))
            wrapper.eq(Menu::getStatus,status);
        if (Strings.hasText(menuName))
            wrapper.like(Menu::getMenuName,menuName);
        return ResponseResult.okResult(list(wrapper));
    }

    @Override
    public ResponseResult getTree(Long id) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getParentId,0L);
        List<Menu> menus = list(wrapper);
        List<MenuTreeNode> nodes = new ArrayList<>();
        for (Menu menu : menus) {
            MenuTreeNode treeNode = new MenuTreeNode(null,menu.getId(),menu.getMenuName(),menu.getParentId());
            nodes.add(treeNode);
        }
        for (MenuTreeNode node : nodes) {
            dfs(node);
        }
        RoleAndMenuVo roleAndMenuVo = new RoleAndMenuVo(nodes,menuMapper.selectMenuIdByRoleId(id));
        return ResponseResult.okResult(roleAndMenuVo);
    }

    private void dfs(MenuTreeNode node) {
        if (node == null)
            return;
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getParentId,node.getId());
        List<Menu> menus = list(wrapper);
        if (menus ==null){
            return;
        }
        List<MenuTreeNode> nodes = new ArrayList<>();
        for (Menu menu : menus) {
            MenuTreeNode treeNode = new MenuTreeNode(null,menu.getId(),menu.getMenuName(),menu.getParentId());
            nodes.add(treeNode);
        }
        node.setChildren(nodes);
        for (MenuTreeNode child : node.getChildren()) {
            dfs(child);
        }
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
        wrapper.and(i ->i.eq(Menu::getMenuType,"C").or().eq(Menu::getMenuType,"M"));
//        wrapper.eq(Menu::getMenuType,"C").or().
//        eq(Menu::getMenuType,"M");
        List<Menu> list = list(wrapper);
        HashSet<Long> menuIdByUserId = menuMapper.selectMenuIdByUserId(userId);
        //列表中存在菜单的id在哈希集合中,则移出
        list.removeIf(menu -> menuIdByUserId.contains(menu.getId()));
        return BeanCopyUtils.copyBeanList(list(wrapper), MenuVo.class);
    }
}


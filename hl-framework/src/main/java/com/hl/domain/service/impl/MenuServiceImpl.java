package com.hl.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hl.domain.mapper.MenuMapper;
import com.hl.domain.entity.Menu;
import com.hl.domain.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2023-03-21 18:22:56
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}


package com.hl.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : hupo, 创建于:2023/3/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleAndMenuVo {
    List<MenuTreeNode> menus;
    List<Long> checkedKeys;
}

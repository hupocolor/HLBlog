package com.hl.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : hupo, 创建于:2023/3/25
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleMenu {
    private Long RoleId;
    private Long MenuId;
}

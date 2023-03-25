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
public class RoleVo {
    private Long id;
    private String remark;
    private String roleKey;
    private String roleName;
    private Integer roleSort;
    private Integer status;
    private List<Long> menuIds;
}

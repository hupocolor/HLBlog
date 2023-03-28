package com.hl.domain.vo;

import com.hl.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : hupo, 创建于:2023/3/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolesByUserIdVo {
    List<Long> roleIds;
    List<Role> roles;
    UserInfoVo user;
}

package com.hl.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : hupo, 创建于:2023/3/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserDto {
    Long id;
    String userName;
    String nickName;
    String password;
    String phonenumber;
    String email;
    String sex;
    String status;
    List<Long> roleIds;
}

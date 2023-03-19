package com.hl.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author : hupo, 创建于:2023/3/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserInfoVo {
    private Long id;
    private String nickName;
    private String avatar;
    private String sex;
    private String email;
}

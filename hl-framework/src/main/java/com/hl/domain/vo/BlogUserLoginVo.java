package com.hl.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : hupo, 创建于:2023/3/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUserLoginVo {
    private String token;
    private UserInfoVo userInfo;
}

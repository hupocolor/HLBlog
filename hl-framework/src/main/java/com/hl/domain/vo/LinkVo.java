package com.hl.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : hupo, 创建于:2023/3/15
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LinkVo {
    private String address;
    private String description;
    private Long id;
    private String logo;
    private String name;
}

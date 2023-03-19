package com.hl.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : hupo, 创建于:2023/3/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotArticleVo {
    private Long blogId;
    private String blogTitle;
    private Long blogViews;
}

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
public class CategoryVo {
    private Integer categoryId;
    private Integer id;
    private String name;
    private String description;
    public CategoryVo idToCategoryId(){
        this.categoryId = this.id;
        return this;
    }
    public CategoryVo categoryIdToId(){
        this.id = categoryId;
        return this;
    }
}

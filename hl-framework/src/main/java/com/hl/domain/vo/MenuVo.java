package com.hl.domain.vo;

import com.hl.domain.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author : hupo, 创建于:2023/3/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuVo {
    private List<MenuVo> children = new ArrayList<>();
    private String component;
    private Date createTime;
    private String icon;
    private Long id;
    private String menuName;
    private String menuType;
    private Integer orderNum;
    private Long parentId;
    private String path;
    private String perms;
    private String status;
    private String visible;
}

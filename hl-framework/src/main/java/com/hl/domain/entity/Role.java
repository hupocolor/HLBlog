package com.hl.domain.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (Role)表实体类
 *
 * @author makejava
 * @since 2023-03-21 19:25:20
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role")
public class Role {
    @TableId
    private Long id;
    
    private String roleName;
    
    private String roleKey;
    
    private Integer roleSort;
    
    private Integer status;
    
    private Integer delFlag;
    
    private String creatBy;
    
    private Date creatTime;
    
    private String updateBy;
    
    private Date updateTime;
    
    private String remark;

}


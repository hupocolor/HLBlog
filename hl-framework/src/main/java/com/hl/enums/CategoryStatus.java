package com.hl.enums;

/**
 * @Author : hupo, 创建于:2023/3/13
 */
public enum CategoryStatus {
    BAN("1"),NORMAL("0");
    private String status;

    private CategoryStatus(String status) {
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
}

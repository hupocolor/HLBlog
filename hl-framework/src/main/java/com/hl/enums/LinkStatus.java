package com.hl.enums;

/**
 * @Author : hupo, 创建于:2023/3/15
 */
public enum LinkStatus {
    WAIT_PASS("2"),NOT_PASS("1"),PASS("0");
    private String status;
    private LinkStatus(String status){
        this.status = status;
    }
}

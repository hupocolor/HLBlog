package com.hl.enums;/**
 * @Author : hupo, 创建于:2023/3/13
 */
public enum ArticleStatus {
    DRAFT(0),NORMAL(1),IS_TOP(1),IS_NOT_TOP(0),ENABLE_COMMENT(0),NOT_ENABLE_COMMENT(1);
    private int status;
    private ArticleStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
}

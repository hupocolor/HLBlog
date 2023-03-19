package com.hl.exception;

import com.hl.enums.AppHttpCodeEnum;
import lombok.Getter;

/**
 * @Author : hupo, 创建于:2023/3/16
 */
@Getter
public class SysException extends RuntimeException{
    private int code;
    private String msg;

    public SysException(AppHttpCodeEnum httpCodeEnum){
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}

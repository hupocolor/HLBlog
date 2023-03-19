package com.hl.handler.exception;

import com.hl.domain.ResponseResult;
import com.hl.enums.AppHttpCodeEnum;
import com.hl.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author : hupo, 创建于:2023/3/16
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SysException.class)
    public ResponseResult sysExcHandler(SysException e){
        //打印异常信息
        log.error("出现异常! {}",e);
        //从异常对象中获取提示信息
        //封装返回
        return ResponseResult.errorResult(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult globalExcHandler(Exception e){
        //打印异常信息
        log.error("出现异常! {}",e);
        //从异常对象中获取提示信息
        //封装返回
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage());
    }

}

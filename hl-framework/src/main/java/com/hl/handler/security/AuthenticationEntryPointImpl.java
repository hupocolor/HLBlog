package com.hl.handler.security;

import com.alibaba.fastjson.JSON;
import com.hl.domain.ResponseResult;
import com.hl.enums.AppHttpCodeEnum;
import com.hl.utils.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author : hupo, 创建于:2023/3/16
 * 认证失败处理器
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(),authException.getMessage());
        //BadCredentialsException
        //响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}

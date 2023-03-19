package com.hl.aspect;

import com.alibaba.fastjson2.JSON;
import com.hl.annotation.SystemLog;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

/**
 * @Author : hupo, 创建于:2023/3/19
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(com.hl.annotation.SystemLog)")
    public void point(){

    }
    @Around("point()")
    public Object pointLog(ProceedingJoinPoint joinPoint) throws Throwable {

        Object ret;
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handleAfter(ret);
        } finally {
            handleFinally();
        }

        return ret;
    }
    private void handleBefore(ProceedingJoinPoint joinPoint){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //获取被增强方法的注解对象
        SystemLog systemLog = getSystemLog(joinPoint);
        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}",request.getRequestURI());
        // 打印描述信息
        log.info("BusinessName   : {}",systemLog.detailInfo());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(),((MethodSignature) joinPoint.getSignature()).getMethod());
        // 打印请求的 IP
        log.info("IP             : {}",request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", JSON.toJSON(joinPoint.getArgs()));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getAnnotation(SystemLog.class);
    }

    private void handleAfter(Object ret){
        // 打印出参
        log.info("Response       : {}", JSON.toJSON(ret));
    }
    private void handleFinally(){
        log.info("===============end==============="+System.lineSeparator());
    }
}

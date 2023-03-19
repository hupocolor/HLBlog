package com.hl.controller;

import com.hl.annotation.SystemLog;
import com.hl.domain.ResponseResult;
import com.hl.domain.entity.User;
import com.hl.domain.service.BlogLoginService;
import com.hl.enums.AppHttpCodeEnum;
import com.hl.exception.SysException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : hupo, 创建于:2023/3/15
 */
@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;
    @PostMapping("/login")
    @SystemLog(detailInfo = "登录操作")
    public ResponseResult login(@RequestBody User user){
        if (user.getUserName() == null || user.getUserName().equals("") ){
            //提示 传给用户必须传入用户名
            throw new SysException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    @SystemLog(detailInfo = "注销操作")
    public ResponseResult logout(){
        //删除redis中的用户信息
        return blogLoginService.logout();
    }
}

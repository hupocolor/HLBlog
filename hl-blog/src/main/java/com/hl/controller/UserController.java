package com.hl.controller;

import com.hl.annotation.SystemLog;
import com.hl.domain.ResponseResult;
import com.hl.domain.entity.User;
import com.hl.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : hupo, 创建于:2023/3/18
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/userInfo")
    @SystemLog(detailInfo = "获取用户信息")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }
    @PutMapping("/userInfo")
    @SystemLog(detailInfo = "更新用户信息")
    public ResponseResult upDataUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    @SystemLog(detailInfo = "注册信息")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }
}

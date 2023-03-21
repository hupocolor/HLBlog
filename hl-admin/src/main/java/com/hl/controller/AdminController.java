package com.hl.controller;

import com.hl.domain.ResponseResult;
import com.hl.domain.entity.User;
import com.hl.domain.service.BlogLoginService;
import com.hl.domain.vo.AdminUserInfoVo;
import com.hl.enums.AppHttpCodeEnum;
import com.hl.exception.SysException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : hupo, 创建于:2023/3/21
 */
@RestController
@RequestMapping
public class AdminController {
    @Autowired
    BlogLoginService blogLoginService;
    @PostMapping("user/login")
    public ResponseResult adminLogin(@RequestBody User user){
        if (user.getUserName() == null || user.getUserName().equals("") ){
            //提示 传给用户必须传入用户名
            throw new SysException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.adminLogin(user);
    }

    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        return blogLoginService.getAdminInfo();
    }
}

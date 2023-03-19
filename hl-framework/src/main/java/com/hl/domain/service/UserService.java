package com.hl.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hl.domain.ResponseResult;
import com.hl.domain.entity.User;
import com.hl.domain.vo.UserInfoVo;

/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-03-18 15:54:59
 */
public interface UserService extends IService<User> {

     ResponseResult register(User user);
    ResponseResult userInfo();
    ResponseResult updateUserInfo(User user);
}


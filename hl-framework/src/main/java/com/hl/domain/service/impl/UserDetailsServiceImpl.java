package com.hl.domain.service.impl;

import com.hl.domain.entity.LoginUser;
import com.hl.domain.entity.User;
import com.hl.domain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author : hupo, 创建于:2023/3/15
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        User user = userMapper.selectByUserName(username);
        //判断是否查到用户 如果没查到,抛出异常
        if (Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }
        //返回用户信息
        //TODO 查询权限信息封装
        return new LoginUser(user);
    }
}

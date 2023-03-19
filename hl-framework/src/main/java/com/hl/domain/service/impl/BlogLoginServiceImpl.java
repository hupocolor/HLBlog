package com.hl.domain.service.impl;

import com.hl.domain.ResponseResult;
import com.hl.domain.entity.LoginUser;
import com.hl.domain.entity.User;
import com.hl.domain.service.BlogLoginService;
import com.hl.domain.vo.BlogUserLoginVo;
import com.hl.domain.vo.UserInfoVo;
import com.hl.utils.BeanCopyUtils;
import com.hl.utils.JwtUtil;
import com.hl.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author : hupo, 创建于:2023/3/15
 */
@Service
public class BlogLoginServiceImpl implements BlogLoginService {
    @Autowired
    RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userId生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(id);
        //把用户信息存入redis
        redisCache.setCacheObject("bloglogin:"+id,loginUser);
        //把token和userinfo封装 返回
//        System.out.println(loginUser.getUser());
        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(jwt, BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class));
        return ResponseResult.okResult(blogUserLoginVo);
    }

    @Override
    public ResponseResult logout() {
        //从token获取userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();
        //获取userId
        redisCache.deleteObject("bloglogin:"+id);
        return ResponseResult.okResult();
    }
}

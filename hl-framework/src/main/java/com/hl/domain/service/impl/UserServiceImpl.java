package com.hl.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hl.domain.ResponseResult;
import com.hl.domain.entity.LoginUser;
import com.hl.domain.mapper.UserMapper;
import com.hl.domain.entity.User;
import com.hl.domain.service.UserService;
import com.hl.domain.vo.UserInfoVo;
import com.hl.enums.AppHttpCodeEnum;
import com.hl.exception.SysException;
import com.hl.utils.BeanCopyUtils;
import com.hl.utils.RedisCache;
import com.hl.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-03-18 15:54:59
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    RedisCache redisCache;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public ResponseResult register(User user) {
        //进行非空判断
        if (user.getUserName() ==null || user.getNickName() == null
        || user.getEmail() == null || user.getPassword() == null){
            throw new SysException(AppHttpCodeEnum.USER_INFO_ERROR);
        }
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName, user.getUserName());
        if (getOne(lambdaQueryWrapper) != null){
            throw new SysException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        LambdaQueryWrapper<User> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.eq(User::getEmail, user.getEmail());
        if (getOne(lambdaQueryWrapper1) != null){
            throw new SysException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        if (!judgePassword(user.getPassword())){
            throw  new SysException(AppHttpCodeEnum.LOGIN_ERROR);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        save(user);
        return ResponseResult.okResult();
    }
    public boolean judgePassword(String password){
        //定义一个正则表达式，要求密码长度为6-18位，只包含英文，数字和下划线
        String regex = "^(?=.*\\w)(?=\\S+$).{6,18}$";
        //使用Pattern和Matcher类来验证密码是否匹配正则表达式
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        //返回匹配结果
        return m.matches();
    }

    @Override
    public ResponseResult userInfo() {
        //获取id
        User user = SecurityUtils.getLoginUser().getUser();
        //查询信息
        //封装成功,返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        LoginUser loginUser = new LoginUser(getById(user.getId()));
        redisCache.setCacheObject("bloglogin:"+user.getId(),loginUser);
        return ResponseResult.okResult();
    }
}


package com.hl.domain.service;

import com.hl.domain.ResponseResult;
import com.hl.domain.entity.User;
import org.springframework.stereotype.Service;

/**
 * @Author : hupo, 创建于:2023/3/15
 */
public interface BlogLoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}

package com.hl.controller;

import com.hl.domain.ResponseResult;
import com.hl.domain.dto.AddUserDto;
import com.hl.domain.entity.User;
import com.hl.domain.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : hupo, 创建于:2023/3/28
 */
@RestController
@RequestMapping("/system/user")
public class AdminUserController {
    @Autowired
    UserService userService;
    @GetMapping("/list")
    public ResponseResult listByKeywords(Integer pageNum,Integer pageSize,String userName,String phonenumber,String status){
        return userService.listByKeywords(pageNum,pageSize,userName,phonenumber,status);
    }

    @PostMapping()
    public ResponseResult addUser(@RequestBody AddUserDto addUserDto){
        return userService.addUser(addUserDto);
    }

    @DeleteMapping("{id}")
    public ResponseResult deleteUser(@PathVariable Long id){
        return ResponseResult.okResult(userService.removeById(id));
    }

    @GetMapping("id")
    public ResponseResult getRoleByUserId(@PathVariable Long id){
        return userService.getRoleByUserId(id);
    }

    @PutMapping("id")
    public ResponseResult updateUserRoles(@RequestBody AddUserDto userDto){
        return userService.updateUserRoles(userDto);
    }
}

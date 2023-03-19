package com.hl.controller;

import com.hl.annotation.SystemLog;
import com.hl.domain.ResponseResult;
import com.hl.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : hupo, 创建于:2023/3/13
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/getCategoryList")
    @SystemLog(detailInfo = "获取分类列表")
    public ResponseResult getCategoryList(){
        return categoryService.getCategoryList();
    }
}

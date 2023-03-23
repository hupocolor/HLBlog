package com.hl.controller;

import com.hl.domain.ResponseResult;
import com.hl.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : hupo, 创建于:2023/3/23
 */
@RestController
@RequestMapping("/content/category")
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory(){
        return categoryService.getCategoryAll();
    }
}

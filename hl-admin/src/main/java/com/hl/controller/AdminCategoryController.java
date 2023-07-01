package com.hl.controller;

import com.hl.domain.ResponseResult;
import com.hl.domain.entity.Category;
import com.hl.domain.service.CategoryService;
import com.hl.domain.vo.CategoryVo;
import com.hl.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/list")
    public ResponseResult listCategory(Integer pageNum,Integer pageSize,String name,String status){
        return categoryService.getCategoryListPage(pageNum,pageSize,name,status);
    }

    @PostMapping()
    public ResponseResult addCategory(@RequestBody Category category){
        categoryService.save(category);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult getById(@PathVariable Long id){
        return ResponseResult.okResult(BeanCopyUtils.copyBean(categoryService.getById(id),CategoryVo.class).categoryIdToId());
    }

    @PutMapping()
    public ResponseResult updateById(@RequestBody CategoryVo categoryVo){
        return ResponseResult.okResult(categoryService.updateById(BeanCopyUtils.copyBean(categoryVo.idToCategoryId(),Category.class)));
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteById(@PathVariable Long id){
        categoryService.removeById(id);
        return ResponseResult.okResult();
    }
}

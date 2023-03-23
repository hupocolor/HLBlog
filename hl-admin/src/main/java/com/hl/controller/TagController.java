package com.hl.controller;

import com.hl.annotation.SystemLog;
import com.hl.domain.ResponseResult;
import com.hl.domain.dto.TagListDto;
import com.hl.domain.entity.Tag;
import com.hl.domain.service.TagService;
import com.hl.domain.vo.PageVo;
import com.hl.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Author : hupo, 创建于:2023/3/21
 */
@RestController
@RequestMapping("content/tag")
public class TagController {
    @Autowired
    TagService tagService;
    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }
    @GetMapping("/listAllTag")
    public ResponseResult getAllList(){
        return ResponseResult.okResult(tagService.list());
    }

    @PostMapping()
    public ResponseResult addTag(@RequestBody TagListDto tagDto){
        Tag tag = BeanCopyUtils.copyBean(tagDto, Tag.class);
        return ResponseResult.okResult(tagService.saveOrUpdate(tag));
    }

    @DeleteMapping("/{id}")
    @SystemLog(detailInfo = "删除标签")
    public ResponseResult deleteTag(@PathVariable("id") Long id){
        return ResponseResult.okResult(tagService.removeById(id));
    }

    @GetMapping("/{id}")
    public ResponseResult getTag(@PathVariable("id") Long id){
        HashMap<String,Object> hashMap = new HashMap<>();
        Tag byId = tagService.getById(id);
        hashMap.put("id",byId.getId());
        hashMap.put("name",byId.getName());
        hashMap.put("remark",byId.getRemark());
        return ResponseResult.okResult(hashMap);
    }

    @PutMapping()
    public ResponseResult updateTag(@RequestBody Tag tag){
        return ResponseResult.okResult(tagService.updateById(tag));
    }
}

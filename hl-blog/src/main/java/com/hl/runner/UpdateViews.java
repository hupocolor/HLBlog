package com.hl.runner;

import com.hl.domain.entity.Blog;
import com.hl.domain.service.BlogService;
import com.hl.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author : hupo, 创建于:2023/3/19
 */
@Component
@EnableScheduling
public class UpdateViews {
    @Autowired
    RedisCache redisCache;

    @Autowired
    BlogService blogService;
    @Scheduled(cron = "* 0/5 * * * ?")
    public void updateViews(){
        System.out.println("定时任务执行");
        Map<String, Integer> map = redisCache.getCacheMap("blog:blogViews");
        List<Blog> blogs = blogService.list();
        for (Blog blog : blogs) {
            blog.setBlogViews(Long.valueOf(map.get(blog.getBlogId().toString())));
            blogService.updateById(blog);
        }
    }
}

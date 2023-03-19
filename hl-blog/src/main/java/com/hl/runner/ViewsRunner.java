package com.hl.runner;

import com.hl.domain.entity.Blog;
import com.hl.domain.mapper.BlogMapper;
import com.hl.domain.service.BlogService;
import com.hl.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author : hupo, 创建于:2023/3/19
 */
@Component
public class ViewsRunner implements CommandLineRunner {
    @Autowired
    BlogMapper blogMapper;

    @Autowired
    RedisCache redisCache;
    @Override
    public void run(String... args) throws Exception {
        //查询博客信息 id views
        List<Blog> blogs = blogMapper.selectList(null);
        Map<String, Integer> viewsMap = blogs.stream().
                collect(Collectors.toMap(blog -> blog.getBlogId().toString(),blog -> {
                    return blog.getBlogViews().intValue();
                }));
        redisCache.setCacheMap("blog:blogViews",viewsMap);
    }
}

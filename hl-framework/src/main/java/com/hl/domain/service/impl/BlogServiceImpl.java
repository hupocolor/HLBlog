package com.hl.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hl.domain.ResponseResult;
import com.hl.domain.dto.AddArticleDto;
import com.hl.domain.entity.BlogTag;
import com.hl.domain.mapper.BlogMapper;
import com.hl.domain.entity.Blog;
import com.hl.domain.mapper.CategoryMapper;
import com.hl.domain.service.BlogService;
import com.hl.domain.service.BlogTagService;
import com.hl.domain.service.CategoryService;
import com.hl.domain.vo.ArticleByCategoryVo;
import com.hl.domain.vo.BlogDetailVo;
import com.hl.domain.vo.HotArticleVo;
import com.hl.domain.vo.PageVo;
import com.hl.enums.ArticleStatus;
import com.hl.utils.ArticleUtils;
import com.hl.utils.BeanCopyUtils;
import com.hl.utils.RedisCache;
import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * (Blog)表服务实现类
 *
 * @author makejava
 * @since 2023-03-12 15:48:02
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    RedisCache redisCache;
    @Autowired
    BlogTagService blogTagService;

    @Override
    public ResponseResult getHotList() {
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        //必须是非草稿文章
        queryWrapper.eq(Blog::getBlogStatus, ArticleStatus.NORMAL.getStatus());
        //按照浏览量降序排序
        queryWrapper.orderByDesc(Blog::getBlogViews);
        //分页查询来获得
        Page<Blog> page = new Page(1, 10);
        page(page, queryWrapper);
        List<Blog> blogs = page.getRecords();
        //使用流处理将blogs的数据映射
        List<HotArticleVo> resList = BeanCopyUtils.copyBeanList(blogs, HotArticleVo.class);
        return ResponseResult.okResult(resList);
    }

    @Override
    //定义一个方法返回博客列表
    public ResponseResult blogList(Integer pageNum, Integer pageSize, Integer categoryId) {
        //创建一个查询条件对象
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper();
        //如果分类id不为空且不为0，则添加分类id作为查询条件
        if (categoryId != null && !categoryId.equals(0)) {
            queryWrapper.eq(Blog::getBlogCategoryId, categoryId);
        }
        //只查询状态为正常的博客，并按照是否置顶降序排序
        queryWrapper.eq(Blog::getBlogStatus, ArticleStatus.NORMAL.getStatus());
        queryWrapper.orderByDesc(Blog::getIsTop);
        //创建一个分页对象，并传入页码和每页大小
        Page<Blog> blogPage = new Page<>(pageNum, pageSize);
        //调用分页查询方法，并传入分页对象和查询条件对象
        page(blogPage, queryWrapper);
        //获取分页结果中的博客列表
        List<Blog> blogs = blogPage.getRecords();
        //遍历博客列表，根据分类id获取分类名称，并设置到博客对象中
        for (Blog blog : blogs) {
            blog.setCategoryName(categoryMapper.selectById(blog.getBlogCategoryId()).getName());
        }
        //将博客列表转换成ArticleByCategoryVo类型的列表，并返回给前端
        List<ArticleByCategoryVo> resList = BeanCopyUtils.copyBeanList(blogs, ArticleByCategoryVo.class);
        PageVo pageVo = new PageVo(resList, blogPage.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getDetail(Long id) {
        Blog blogDetail = getById(id);
        BlogDetailVo blogDetailVo = BeanCopyUtils.copyBean(blogDetail, BlogDetailVo.class);
        blogDetailVo.setCategoryName(categoryMapper.selectById(blogDetailVo.getBlogCategoryId()).getName());
        return ResponseResult.okResult(blogDetailVo);
    }

    @Override
    public ResponseResult updateViews(Long id) {
        redisCache.incrementCacheMapValue("blog:blogViews", id.toString(), 1);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult add(AddArticleDto article) {
        Blog blog = ArticleUtils.ArticleDtotoBlog(article);
        save(blog);
        List<BlogTag> articleTags = article.getTags().stream()
                .map(tagId -> new BlogTag(blog.getBlogId(), tagId))
                .collect(Collectors.toList());
        blogTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getListByKeyWords(Integer pageNum, Integer pageSize, String title, String summary) {
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        if (Strings.hasText(title))
            wrapper.like(Blog::getBlogTitle, title);
        if (Strings.hasText(summary))
            wrapper.like(Blog::getBlogSummary, summary);
        Page<Blog> blogPage = new Page<>(pageNum, pageSize);
        //调用分页查询方法，并传入分页对象和查询条件对象
        page(blogPage, wrapper);
        //获取分页结果中的博客列表
        List<Blog> blogs = blogPage.getRecords();
        List<AddArticleDto> res = ArticleUtils.ListBlogToDto(blogs);
        return ResponseResult.okResult(new PageVo(res, blogPage.getTotal()));
    }

}


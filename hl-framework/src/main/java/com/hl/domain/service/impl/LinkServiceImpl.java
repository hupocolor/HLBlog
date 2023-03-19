package com.hl.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hl.domain.ResponseResult;
import com.hl.domain.mapper.LinkMapper;
import com.hl.domain.entity.Link;
import com.hl.domain.service.LinkService;
import com.hl.domain.vo.LinkVo;
import com.hl.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2023-03-15 13:47:46
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
    @Autowired
    LinkMapper linkMapper;
    @Override
    public ResponseResult getAllLinkResult() {
        List<Link> list = linkMapper.selectByStatus();
        return ResponseResult.okResult(BeanCopyUtils.copyBeanList(list, LinkVo.class));
    }
}


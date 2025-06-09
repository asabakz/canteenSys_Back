package com.item.web.Comment.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.item.web.Comment.entity.Comment;
import com.item.web.Comment.entity.CommentParm;
import com.item.web.Comment.mapper.CommentMapper;
import com.item.web.Comment.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Override
    public IPage<Comment> getList(CommentParm parm) {
        Page<Comment> page = new Page<>(parm.getCurrentPage(), parm.getPageSize());
        return this.baseMapper.getList(page);
    }
}


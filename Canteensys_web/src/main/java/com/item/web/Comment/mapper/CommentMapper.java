package com.item.web.Comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.item.web.Comment.entity.Comment;

public interface CommentMapper extends BaseMapper<Comment> {
   IPage<Comment> getList(Page<Comment> page);
}

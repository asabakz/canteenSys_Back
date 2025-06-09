package com.item.web.Comment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.item.web.Comment.entity.Comment;
import com.item.web.Comment.entity.CommentParm;

public interface CommentService extends IService<Comment> {
    IPage<Comment> getList(CommentParm page);
}

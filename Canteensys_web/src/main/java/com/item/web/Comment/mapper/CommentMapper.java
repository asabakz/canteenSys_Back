package com.item.web.Comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.item.web.Comment.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {
   IPage<Comment> getList(Page<Comment> page);
   List<Comment> commentList(@Param("goodsId") Long goodsId);
}



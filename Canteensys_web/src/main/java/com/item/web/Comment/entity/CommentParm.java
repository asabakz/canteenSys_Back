package com.item.web.Comment.entity;

import lombok.Data;

@Data
public class CommentParm {
    private Integer currentPage;//当前页面
    private Integer PageSize; //页面显示条数
}

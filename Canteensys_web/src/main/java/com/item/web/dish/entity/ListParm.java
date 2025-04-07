package com.item.web.dish.entity;

import lombok.Data;

@Data
public class ListParm {
    private Integer currentPage;//当前页面
    private Integer PageSize; //页面显示条数
    private String  dishName;
}

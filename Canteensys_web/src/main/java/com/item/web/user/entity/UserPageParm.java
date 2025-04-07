package com.item.web.user.entity;
import lombok.Data;

//接受前端传送的参数
@Data
public class UserPageParm {
    private Integer currentPage;//当前页面
    private Integer PageSize; //页面显示条数
    private String user_name;
    private String phone_number;

}

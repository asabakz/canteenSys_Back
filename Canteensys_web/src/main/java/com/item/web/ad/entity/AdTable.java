package com.item.web.ad.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ad_table")
public class AdTable {
   @TableId(type = IdType.AUTO)
    private Long adId;
    private Long dishId;
    private String title;
    private String dishImage;
    private String onload;
    private Long orderNum;
}

package com.item.web.user_order_detail.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("user_order_detail")
public class UserOrderDetail {
    @TableId(type = IdType.AUTO)
    private Long detailId;
    private Long orderId;
    private Long dishId;
    private String dishImage;
    private String dishName;
    private String dishUnit;
    private String specsName;
    private BigDecimal price;
    private Integer num;
}


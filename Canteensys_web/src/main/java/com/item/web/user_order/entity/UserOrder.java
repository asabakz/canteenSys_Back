package com.item.web.user_order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("user_order")
public class UserOrder {
    @TableId(type = IdType.AUTO)
    private Long orderId;
    private String openid;
    private String userName;
    private String phone;
    private String address;
    private BigDecimal price;
    private Date createTime;
    private String status;
}

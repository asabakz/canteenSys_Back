package com.item.web.user_order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.item.web.user_order_detail.entity.UserOrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@TableName("user_order")
public class UserOrder {
    @TableId(type = IdType.AUTO)
    private Long orderId;
    private String openId;
    private String userName;
    private String phone;
    private String address;
    private BigDecimal price;
    private Date createTime;
    private String status;

    @TableField(exist = false)
    private List<UserOrderDetail> dishList = new ArrayList<>();
}

package com.item.web.user_order.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderParm {
    private String openId;
    private String userName;
    private String phone;
    private String address;
    private BigDecimal price;
    private List<ParmDetail> details = new ArrayList<>();
}


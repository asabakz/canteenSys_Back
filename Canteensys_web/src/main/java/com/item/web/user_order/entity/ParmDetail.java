package com.item.web.user_order.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ParmDetail {
    private Long dishId;
    private String dishImage;
    private String dishName;
    private String dishUnit;
    private String specsName;
    private BigDecimal price;
    private Integer num;
}


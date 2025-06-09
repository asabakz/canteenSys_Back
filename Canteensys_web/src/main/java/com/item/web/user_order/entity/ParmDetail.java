package com.item.web.user_order.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ParmDetail {
    private Long goodsId;
    private String goodsImage;
    private String goodsName;
    private String goodsUnit;
    private String specsName;
    private BigDecimal price;
    private Integer num;
}


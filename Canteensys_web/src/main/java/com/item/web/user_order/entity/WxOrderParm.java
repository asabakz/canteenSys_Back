package com.item.web.user_order.entity;

import lombok.Data;

@Data
public class WxOrderParm {
    private String openid;
    private String type;
    private String userName;
    private Long currentPage;
    private Long pagesize;
}


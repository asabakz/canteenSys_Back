package com.item.web.wechat_api.entity;

import lombok.Data;

@Data
public class LoginVo {
    private String openid;
    private String sessionKey;
}


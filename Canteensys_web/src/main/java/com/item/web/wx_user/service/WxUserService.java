package com.item.web.wx_user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.item.web.wx_user.entity.WxUser;

public interface WxUserService extends IService<WxUser> {
    int saveOrUpdateInfo(WxUser wxUser);
}

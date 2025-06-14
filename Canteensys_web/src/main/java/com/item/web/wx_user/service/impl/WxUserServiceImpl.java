package com.item.web.wx_user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.item.web.wx_user.entity.WxUser;
import com.item.web.wx_user.mapper.WxUserMapper;
import com.item.web.wx_user.service.WxUserService;
import org.springframework.stereotype.Service;

@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements WxUserService {
    @Override
    public int saveOrUpdateInfo(WxUser wxUser) {
        return this.baseMapper.saveOrUpdateInfo(wxUser);
    }
}

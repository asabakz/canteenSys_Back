package com.item.web.wx_user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.item.web.wx_user.entity.WxUser;
import org.apache.ibatis.annotations.Param;

public interface WxUserMapper extends BaseMapper<WxUser> {
    int saveOrUpdateInfo(@Param("user")WxUser user);
}

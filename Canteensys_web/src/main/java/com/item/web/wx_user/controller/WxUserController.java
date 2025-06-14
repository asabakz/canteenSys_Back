package com.item.web.wx_user.controller;

import com.item.utils.ResultUtils;
import com.item.utils.ResultVo;
import com.item.web.user.entity.UserTable;
import com.item.web.wx_user.entity.WxUser;
import com.item.web.wx_user.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author java实体类生成
 * @Version 2383404558
 */
@RestController
@RequestMapping("/wxapi/wxUser")
public class WxUserController {

    @Autowired
    private WxUserService wxUserService;

    @PostMapping("/saveOrUpdate")
    public ResultVo saveOrUpdate(@RequestBody WxUser wxUser) {
        wxUserService.saveOrUpdate(wxUser);
        return ResultUtils.success("更新成功");
    }

    @GetMapping("/getUserInfo")
    public ResultVo getUserInfo(String openid) {
        WxUser user = wxUserService.getById(openid);
        return ResultUtils.error("查询成功!", user);
    }
}


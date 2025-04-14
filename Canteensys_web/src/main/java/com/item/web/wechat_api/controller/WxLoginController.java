package com.item.web.wechat_api.controller;

import com.item.utils.*;

import com.item.web.wechat_api.entity.Code2Session;
import com.item.web.wechat_api.entity.LoginParm;
import com.item.web.wechat_api.entity.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.util.concurrent.ListenableFutureCallbackRegistry;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author java实战基地
 * @Version 2383044558
 */
@Slf4j
@RestController
@RequestMapping("/wxapi/user")
public class WxLoginController {
    @Value("${wechat.applets.appId}")
    private String appId;
    @Value("${wechat.applets.appSecret}")
    private String appSecret;

    @PostMapping("/wxLogin")
    public ResultVo wxLogin(@RequestBody LoginParm parm){
        String code = parm.getCode();
        log.info("wxLogin - code: " + code);
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> param = new HashMap<>();
        param.put("appid", appId);
        param.put("secret", appSecret);
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        log.info("param: " + param);


        try {
            // 发送请求
            String wxResult = HttpClientUtil.doGet(url, param);
            // 转换参数
            Code2Session userJson = FastJsonTools.getJson(wxResult, Code2Session.class);
            log.info("userJson: " + userJson);

            if (userJson == null) throw new BusinessException(500, "小程序获取openid失败");

            String openid = userJson.getOpenid();
            String sessionKey = userJson.getSession_key();
            log.info("openid: " + openid);
            log.info("sessionKey: " + sessionKey);

            LoginVo vo = new LoginVo();
            vo.setOpenid(openid);
            vo.setSessionKey(sessionKey);

            return ResultUtils.success("获取成功", vo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(String.format("WxLoginController.wxLogin Error", e));
        }

        return ResultUtils.error("程序获取失败");
    }
}

package com.item.web.user_order.controller;

import com.item.utils.ResultUtils;
import com.item.utils.ResultVo;
import com.item.web.user_order.entity.OrderParm;
import com.item.web.user_order.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wxapi/order")
public class UserOrderController {

    @Autowired
    private UserOrderService userOrderService;


    @PostMapping("/splaceOrder")
    public ResultVo splaceOrder(@RequestBody OrderParm parm){
        userOrderService.splaceOrder(parm);
        return ResultUtils.success("创建成功！");
    }
}


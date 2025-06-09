package com.item.web.user_order.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.item.utils.ResultUtils;
import com.item.utils.ResultVo;
import com.item.web.user_order.entity.*;
import com.item.web.user_order.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //查询订单
    @GetMapping("/getOrderList")
    public ResultVo getOrderList(WxOrderParm parm){
        IPage<UserOrder> orderList = userOrderService.getOrderList(parm);
        return ResultUtils.success("查询成功!", orderList);
    }

    // 查询订单管理端
    @GetMapping("getManageOrderList")
    public ResultVo getManageOrderList(WxOrderParm parm){
        IPage<UserOrder> orderList = userOrderService.getManageOrderList(parm);
        return ResultUtils.success("查询成功!", orderList);
    }

    @PutMapping("/sendorder")
    public ResultVo sendOrder(@RequestBody SendParm parm) {
        // 更新条件
        LambdaUpdateWrapper<UserOrder> query = new LambdaUpdateWrapper<>();
        query.eq(UserOrder::getOrderId, parm.getOrderId())
                .set(UserOrder::getStatus, "1");
        if (userOrderService.update(query)) {
            return ResultUtils.success("更新成功！");
        }
        return ResultUtils.error("更新失败！");
    }

    @GetMapping("/getTotal")
    public ResultVo getTotal(String type) {
        List<SumList> list = null;
        switch (type) {
            case "1":
                list = userOrderService.getMonths();
                break;
            case "2":
                list = userOrderService.getYears();
                break;
            default:
                list = userOrderService.getDays();
        }
        Echarts echarts = new Echarts();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                echarts.getNames().add(list.get(i).getDays());
                echarts.getValues().add(list.get(i).getPrice());
            }
        }
        return ResultUtils.success("查询成功", echarts);
    }


}


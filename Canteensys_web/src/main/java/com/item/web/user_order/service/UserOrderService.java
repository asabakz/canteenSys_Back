package com.item.web.user_order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.item.web.user_order.entity.OrderParm;
import com.item.web.user_order.entity.SumList;
import com.item.web.user_order.entity.UserOrder;
import com.item.web.user_order.entity.WxOrderParm;

import java.util.List;

public interface UserOrderService extends IService<UserOrder> {
    void splaceOrder(OrderParm parm);
    IPage<UserOrder> getOrderList(WxOrderParm parm);
    IPage<UserOrder> getManageOrderList(WxOrderParm parm);

    List<SumList> getDays();

    List<SumList> getMonths();

    List<SumList> getYears();
}

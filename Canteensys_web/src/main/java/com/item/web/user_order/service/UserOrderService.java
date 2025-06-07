package com.item.web.user_order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.item.web.user_order.entity.OrderParm;
import com.item.web.user_order.entity.UserOrder;

public interface UserOrderService extends IService<UserOrder> {
    void splaceOrder(OrderParm parm);
}

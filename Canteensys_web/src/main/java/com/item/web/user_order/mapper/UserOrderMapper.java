package com.item.web.user_order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.item.web.user_order.entity.SumList;
import com.item.web.user_order.entity.UserOrder;

import java.util.List;

public interface UserOrderMapper extends BaseMapper<UserOrder> {
    List<SumList> getDays();

    List<SumList> getMonths();

    List<SumList> getYears();
}

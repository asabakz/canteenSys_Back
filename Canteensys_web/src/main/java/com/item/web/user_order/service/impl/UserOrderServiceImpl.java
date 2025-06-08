package com.item.web.user_order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.item.web.user_order.entity.OrderParm;
import com.item.web.user_order.entity.ParmDetail;
import com.item.web.user_order.entity.UserOrder;
import com.item.web.user_order.entity.WxOrderParm;
import com.item.web.user_order.mapper.UserOrderMapper;
import com.item.web.user_order.service.UserOrderService;
import com.item.web.user_order_detail.entity.UserOrderDetail;
import com.item.web.user_order_detail.service.UserOrderDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements UserOrderService {
    @Autowired
    private UserOrderDetailService userOrderDetailService;
    // 批量插入数据库
    @Override
    public void splaceOrder(OrderParm parm) {
        // 维护订单主表
        UserOrder order = new UserOrder();
        BeanUtils.copyProperties(parm, order);
        order.setCreateTime(new Date());
        order.setStatus("0");
        this.baseMapper.insert(order);

        // 维护子表：订单对应的商品
        int ls = this.baseMapper.insert(order);
        if(ls>0) {
            List<ParmDetail> details = parm.getDetails();
            List<UserOrderDetail> list = new ArrayList<>();
            if (details.size() > 0) {
                for (int i = 0; i < details.size(); i++) {
                    UserOrderDetail detail = new UserOrderDetail();
                    BeanUtils.copyProperties(details.get(i), detail);
                    //关联主表
                    detail.setOrderId(order.getOrderId());
                    list.add(detail);
                }
            }
            // 批量插入数据库
            userOrderDetailService.saveBatch(list);
        }
    }


    @Override
    public IPage<UserOrder> getOrderList(WxOrderParm parm) {
        QueryWrapper<UserOrder> query = new QueryWrapper<>();
        query.lambda().eq(UserOrder::getOpenid, parm.getOpenId())
                .eq(StringUtils.isNotEmpty(parm.getType()), UserOrder::getStatus, parm.getType()).orderByDesc(UserOrder::getCreateTime);
        IPage<UserOrder> page = new Page<>(parm.getCurrentPage(), parm.getPagesize());
        IPage<UserOrder> order = this.baseMapper.selectPage(page, query);

        // 设置订单表
        if(order.getRecords().size() > 0){
            for(int i = 0; i < order.getRecords().size(); i++){
                QueryWrapper<UserOrderDetail> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(UserOrderDetail::getOrderId, order.getRecords().get(i).getOrderId());
                List<UserOrderDetail> list = userOrderDetailService.list(queryWrapper);
                order.getRecords().get(i).setDishList(list);
            }
        }
        return order;
    }

    @Override
    public IPage<UserOrder> getManageOrderList(WxOrderParm parm) {
        QueryWrapper<UserOrder> query = new QueryWrapper<>();
        query.lambda().eq(StringUtils.isNotEmpty(parm.getType()), UserOrder::getStatus, parm.getType())
                .like(StringUtils.isNotEmpty(parm.getUserName()), UserOrder::getUserName, parm.getUserName())
                .orderByDesc(UserOrder::getCreateTime);

        IPage<UserOrder> page = new Page<>(parm.getCurrentPage(), parm.getPagesize());
        IPage<UserOrder> order = this.baseMapper.selectPage(page, query);


        // 设置订单表
        if(order.getRecords().size() > 0){
            for(int i = 0; i < order.getRecords().size(); i++){
                QueryWrapper<UserOrderDetail> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(UserOrderDetail::getOrderId, order.getRecords().get(i).getOrderId());
                List<UserOrderDetail> list = userOrderDetailService.list(queryWrapper);
                order.getRecords().get(i).setDishList(list);
            }
        }
        return order;
    }


}

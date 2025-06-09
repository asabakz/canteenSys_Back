package com.item.web.user_order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.item.web.user_order.entity.*;
import com.item.web.user_order.mapper.UserOrderMapper;
import com.item.web.user_order.service.UserOrderService;
import com.item.web.user_order_detail.entity.UserOrderDetail;
import com.item.web.user_order_detail.service.UserOrderDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements UserOrderService {
    @Autowired
    private UserOrderDetailService userOrderDetailService;
    // 批量插入数据库
    @Override
    @Transactional
    public void splaceOrder(OrderParm parm) {
        // 维护订单主表
        UserOrder order = new UserOrder();
        BeanUtils.copyProperties(parm, order);
        order.setCreateTime(new Date());
        order.setStatus("0");
        // 只插入一次
        int rows = this.baseMapper.insert(order);
        if (rows <= 0) {
            throw new RuntimeException("创建订单失败");
        }

        // MP 会自动回填自增主键
        Long newOrderId = order.getOrderId();

        // 维护子表：订单对应的商品
        List<ParmDetail> details = parm.getDetails();
        if (details != null && !details.isEmpty()) {
            List<UserOrderDetail> detailList = new ArrayList<>(details.size());
            for (ParmDetail d : details) {
                UserOrderDetail detail = new UserOrderDetail();
                BeanUtils.copyProperties(d, detail);
                // 关联主表主键
                detail.setOrderId(newOrderId);
                detailList.add(detail);
            }
            // 批量插入明细
            userOrderDetailService.saveBatch(detailList);
        }
    }


    @Override
    public IPage<UserOrder> getOrderList(WxOrderParm parm) {
        QueryWrapper<UserOrder> query = new QueryWrapper<>();
        query.lambda().eq(UserOrder::getOpenid, parm.getOpenid())
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

    @Override
    public List<SumList> getDays() {
        return this.baseMapper.getDays();
    }

    @Override
    public List<SumList> getMonths() {
        return this.baseMapper.getMonths();
    }


    @Override
    public List<SumList> getYears() {
        return this.baseMapper.getYears();
    }


}

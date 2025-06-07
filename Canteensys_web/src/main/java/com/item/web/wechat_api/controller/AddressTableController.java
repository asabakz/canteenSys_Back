package com.item.web.wechat_api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.item.utils.ResultUtils;
import com.item.utils.ResultVo;
import com.item.web.address.entity.AddressTable;
import com.item.web.address.service.AddressTableService;
import com.item.web.user.service.impl.UserTableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/wxapi/address")
public class AddressTableController {

    @Autowired
    private AddressTableService addressTableService;
    @Autowired
    private UserTableServiceImpl userTableServiceImpl;

    // 新增地址
    @PostMapping
    public ResultVo add(@RequestBody AddressTable addressTables){
        if(addressTableService.save(addressTables)){
            return ResultUtils.success("新增地址成功！");
        }
        return ResultUtils.error("新增地址失败！");
    }

    // 列表
    @GetMapping("/list")
    public ResultVo list(String openid ){
        // 排序：按 status 排序，默认的排在第一条
        QueryWrapper<AddressTable> query = new QueryWrapper<>();
        query.lambda().eq(AddressTable::getOpenId,openid);
        query.lambda().orderByDesc(AddressTable::getStatus);
        List<AddressTable> list = addressTableService.list(query);
        return ResultUtils.success("查询成功", list);
    }

    // 查询默认地址
    @GetMapping("/getAddress")
    public ResultVo getAddress(String openid){
        QueryWrapper<AddressTable> query = new QueryWrapper<>();
        query.lambda().eq(AddressTable::getStatus,"1")
                .eq(AddressTable::getOpenId,openid);
        AddressTable one = addressTableService.getOne(query);
        return ResultUtils.success("查询成功", one);
    }

    @PutMapping
    public ResultVo edit(@RequestBody AddressTable addressTables){
        LambdaUpdateWrapper<AddressTable> query = new LambdaUpdateWrapper<>();
        query.eq(AddressTable::getOpenId,addressTables.getOpenId()).set(AddressTable::getStatus,"0");
        addressTableService.update(query);
        if(addressTableService.updateById(addressTables)){
            return ResultUtils.success("编辑地址成功！");
        }
        return ResultUtils.error("编辑地址成功！");
    }

}



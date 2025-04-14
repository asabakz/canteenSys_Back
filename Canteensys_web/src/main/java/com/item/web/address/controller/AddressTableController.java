package com.item.web.address.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.item.utils.ResultUtils;
import com.item.utils.ResultVo;
import com.item.web.address.entity.AddressTable;
import com.item.web.address.service.AddressTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/address")
public class AddressTableController {

    @Autowired
    private AddressTableService addressTableService;

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
    public ResultVo list(){
        // 排序：按 status 排序，默认的排在第一条
        QueryWrapper<AddressTable> query = new QueryWrapper<>();
        query.lambda().orderByDesc(AddressTable::getStatus);
        List<AddressTable> list = addressTableService.list(query);
        return ResultUtils.success("查询成功", list);
    }

}



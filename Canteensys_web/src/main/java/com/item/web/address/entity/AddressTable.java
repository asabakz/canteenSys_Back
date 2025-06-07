package com.item.web.address.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("address_table")
public class AddressTable {
    @TableId(type = IdType.AUTO)
    private Long addressId;
    private String openId;
    private String userName;
    private String phone;
    private String area;
    private String address;
    private String status;
}



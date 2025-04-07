package com.item.web.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user_table")
public class UserTable implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long userId; // 对应 user_id
    private String userName; // 对应 user_name
    private String password; // 对应 password
    private String phoneNumber; // 对应 phone_number
    private String email; // 对应 email
    private String sex; // 对应 sex
    private String realName; //对应 real_name
}



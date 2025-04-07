package com.item.web.dish.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.item.web.dish_specs.entity.DishSpecsTable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@TableName("dish_table")
public class DishTable {
    @TableId(type = IdType.AUTO)
    private Long dishId;
    private Long categoryId;
    private String dishName;
    private String dishImage;
    private String dishIntro;
    private String hot;
    private String dishUnit;
    private String orderNum;

    @TableField(exist = false)
    List<DishSpecsTable> specs = new ArrayList<>();
}


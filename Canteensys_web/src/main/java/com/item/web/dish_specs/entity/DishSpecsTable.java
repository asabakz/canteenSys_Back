package com.item.web.dish_specs.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

import java.io.Serializable;

@Data
@TableName("dish_specs_table")
public class DishSpecsTable {
    @TableId(type = IdType.AUTO)
    private Long specsId;
    private Long dishId;
    private String specsName;
    private BigDecimal dishPrice;
    private Integer orderNum;
}

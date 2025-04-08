package com.item.web.category.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.item.web.dish.entity.DishTable;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@TableName("category_table")
public class CategoryTable implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long categoryId;
    private String categoryName;
    private Integer orderNum;
    @TableField(exist = false)
    private List<DishTable> dish = new ArrayList<DishTable>();
}
